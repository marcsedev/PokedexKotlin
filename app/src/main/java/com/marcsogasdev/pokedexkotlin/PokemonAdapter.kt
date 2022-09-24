package com.marcsogasdev.pokedexkotlin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PokemonAdapter(private val images:List<String>):RecyclerView.Adapter<PokemonViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {

        val layoutInflater:LayoutInflater=LayoutInflater.from(parent.context)
        return PokemonViewHolder(layoutInflater.inflate(R.layout.item_pokemon,parent,false))
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val item: String = images[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = images.size
}