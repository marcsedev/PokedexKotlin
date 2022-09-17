package com.marcsogasdev.pokedexkotlin.pokeapi

import com.marcsogasdev.pokedexkotlin.models.PokemonRespuesta

interface PokeapiService {
    //ponemos get ya que es una obtención de datos
    // y la parte de la url que falta para acceder a los datos
    @GET()
    @GET("pokemon")
    fun obtenerListaPokemon(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Call<PokemonRespuesta?>?
}
