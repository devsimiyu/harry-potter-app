package com.devsimiyu.harrypotter.utils

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.devsimiyu.harrypotter.R
import com.devsimiyu.harrypotter.model.Spell


class SpellListAdapter(
    private val context: Activity,
    private var spells: List<Spell>
) : ArrayAdapter<String>(context, R.layout.spell_list_item, spells.map { it.name }) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.spell_list_item, null, true)
        val title: TextView = rowView.findViewById(R.id.spell_title)
        val description: TextView = rowView.findViewById(R.id.spell_description)
        title.text = spells[position].name
        description.text = spells[position].description

        return rowView
    }

    fun update(data: List<Spell>): Unit {
        spells = data
        notifyDataSetChanged()
    }
}