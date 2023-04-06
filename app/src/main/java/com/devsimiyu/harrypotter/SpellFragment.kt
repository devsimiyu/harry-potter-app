package com.devsimiyu.harrypotter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.devsimiyu.harrypotter.model.SpellStatus
import com.devsimiyu.harrypotter.utils.SpellListAdapter
import com.devsimiyu.harrypotter.viewmodel.SpellViewModel
import com.google.android.material.snackbar.Snackbar

class SpellFragment : Fragment() {

    private lateinit var view: View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_spell, container, false)

        setupList()

        return view
    }

    private fun setupList(): Unit {
        val listView: ListView = view.findViewById(R.id.spells_list_view)
        val viewModel = ViewModelProvider(this).get(SpellViewModel::class.java)

        viewModel.fetchSpells().observe(viewLifecycleOwner, Observer { state ->
            when (state.status) {
                SpellStatus.LOADING -> {
                    view.findViewById<View?>(R.id.loader).visibility = View.VISIBLE
                    listView.visibility = View.GONE
                }
                SpellStatus.SUCCESS -> {
                    view.findViewById<View?>(R.id.loader).visibility = View.INVISIBLE
                    listView.visibility = View.VISIBLE

                    state.data?.let {
                        val adapter = SpellListAdapter(requireActivity(), it)
                        listView.adapter = adapter
                    }
                }
                SpellStatus.ERROR -> {
                    view.findViewById<View?>(R.id.loader).visibility = View.GONE
                    listView.visibility = View.GONE

                    Snackbar.make(
                        view,
                        "Failed to load spells",
                        Snackbar.LENGTH_LONG
                    ).show()

                }
            }
        }
        )
    }
}