package com.marcsogasdev.pokedexkotlin.service

import com.marcsogasdev.pokedexkotlin.PokemonRespuesta
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface PokeapiService {
    //ponemos get ya que es una obtención de datos
    // y la parte de la url que falta para acceder a los datos
    @GET()
    suspend fun obtenerListaPokemon(@Url url: String): Response<PokemonRespuesta>
}

/*
    @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Call<PokemonRespuesta?>?
}
*/
