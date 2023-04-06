package com.devsimiyu.harrypotter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devsimiyu.harrypotter.model.Character
import com.squareup.picasso.Picasso

class CharacterRecyclerAdpater(private var characters: List<Character>, private val context: Context) : RecyclerView.Adapter<CharacterRecyclerAdpater.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characters[position], context)
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    public fun update(data: List<Character>): Unit {
        characters = data
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Character, context: Context) {
            val name: TextView = itemView.findViewById(R.id.character_name)
            val actor: TextView = itemView.findViewById(R.id.character_actor)
            val image: ImageView = itemView.findViewById(R.id.character_image)
            name.text = data.name
            actor.text = data.actor

            if (!data.image.isBlank()) Picasso.get().load(data.image).into(image)
        }
    }
}
