package com.devsimiyu.harrypotter

import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devsimiyu.harrypotter.model.CharacterStatus
import com.devsimiyu.harrypotter.utils.CharacterRecyclerAdapter
import com.devsimiyu.harrypotter.viewmodel.CharacterViewModel
import com.google.android.material.snackbar.Snackbar


class SearchFragment : Fragment() {

    private lateinit var view: View
    private val viewModel by viewModels<CharacterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        view = inflater.inflate(R.layout.fragment_search, container, false)
        setupRecycler()
        return view
    }

    private fun setupRecycler(): Unit {
        val adapter = CharacterRecyclerAdapter()
        val recyclerView: RecyclerView = view.findViewById(R.id.character_recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        val searchView: SearchView = view.findViewById(R.id.character_search)
        val searchIcon: ImageView = searchView.findViewById(androidx.appcompat.R.id.search_mag_icon)
        searchIcon.setColorFilter(ContextCompat.getColor(requireContext(), R.color.content_title_color), PorterDuff.Mode.SRC_IN)
        searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(text: String?): Boolean {
                    if (text.isNullOrBlank()) adapter.update(emptyList())
                    else viewModel.searchCharacters(text)
                    return true
                }

                override fun onQueryTextChange(text: String?): Boolean {
                    if (text.isNullOrBlank()) adapter.update(emptyList())
                    else viewModel.searchCharacters(text)
                    return true
                }
            },
        )
        viewModel.searchResult.observe(viewLifecycleOwner, Observer { state ->
            when (state.status) {
                CharacterStatus.LOADING -> {
                    view.findViewById<View?>(R.id.loader).visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                CharacterStatus.SUCCESS -> {
                    view.findViewById<View?>(R.id.loader).visibility = View.INVISIBLE
                    recyclerView.visibility = View.VISIBLE
                    adapter.update(state.data ?: emptyList())
                }
                CharacterStatus.ERROR -> {
                    view.findViewById<View?>(R.id.loader).visibility = View.GONE
                    recyclerView.visibility = View.GONE
                    Snackbar.make(
                        view,
                        "Failed to search characters",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        })
    }
}