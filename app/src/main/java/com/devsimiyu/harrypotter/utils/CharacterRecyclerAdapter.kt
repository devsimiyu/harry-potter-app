package com.devsimiyu.harrypotter.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.devsimiyu.harrypotter.R
import com.devsimiyu.harrypotter.model.Character
import com.squareup.picasso.Picasso

class CharacterRecyclerAdapter() : RecyclerView.Adapter<CharacterRecyclerAdapter.ViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<Character>() {

        override fun areItemsTheSame(
            oldItem: Character,
            newItem: Character
        ) : Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Character,
            newItem: Character
        ) : Boolean =  oldItem == newItem
    }

    private val asyncDiff = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = asyncDiff.currentList[position]
        holder.bind(character)
    }

    override fun getItemCount(): Int = asyncDiff.currentList.size

    fun update(data: List<Character>): Unit {
        asyncDiff.submitList(data)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(data: Character) {
            val name: TextView = itemView.findViewById(R.id.character_name)
            val actor: TextView = itemView.findViewById(R.id.character_actor)
            val image: ImageView = itemView.findViewById(R.id.character_image)
            name.text = data.name
            actor.text = data.actor
            if (data.image.isNotBlank()) Picasso.get().load(data.image).into(image)
        }
    }
}
