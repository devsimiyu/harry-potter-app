package com.devsimiyu.harrypotter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devsimiyu.harrypotter.model.CharacterStatus
import com.devsimiyu.harrypotter.utils.CharacterRecyclerAdapter
import com.devsimiyu.harrypotter.viewmodel.CharacterViewModel
import com.google.android.material.snackbar.Snackbar


class StaffFragment : Fragment() {

    private lateinit var view: View
    private val viewModel by viewModels<CharacterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View {
        view = inflater.inflate(R.layout.fragment_staff, container, false)
        setupRecycler()
        return view
    }

    private fun setupRecycler(): Unit {
        val adapter = CharacterRecyclerAdapter()
        val recyclerView: RecyclerView = view.findViewById(R.id.character_recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        viewModel.fetchCharacters().observe(viewLifecycleOwner, Observer { state ->
            when (state.status) {
                CharacterStatus.LOADING -> {
                    view.findViewById<View?>(R.id.loader).visibility = View.VISIBLE
                }
                CharacterStatus.SUCCESS -> {
                    view.findViewById<View?>(R.id.loader).visibility = View.INVISIBLE
                    state.data?.let {
                        val data = it.filter { character ->  character.hogwartsStaff }
                        adapter.update(data)
                    }
                }
                CharacterStatus.ERROR -> {
                    view.findViewById<View?>(R.id.loader).visibility = View.GONE
                    Snackbar.make(
                        view,
                        "Failed to load staff",
                        Snackbar.LENGTH_LONG
                    ).show()

                }
            }
        })
    }
}