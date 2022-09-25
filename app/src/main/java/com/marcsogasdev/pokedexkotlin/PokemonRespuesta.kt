package com.marcsogasdev.pokedexkotlin

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PokemonRespuesta (
  /* @SerializedName("status")var status:String,
   @SerializedName("message") var images:List<String>) {

}*/

   @Expose @SerializedName ("id") var id:Int,
   @Expose @SerializedName ("name") var name:String,
   @Expose @SerializedName ("url") var url:String,
   @Expose @SerializedName ("sprites") var sprites:Sprites)//List<String>){

    //atributos que nos interesan del GSON
    //array del resultado



data class Sprites (

   @Expose @SerializedName("front_default") val frontDefault:String?,
   @Expose @SerializedName("front_shiny") val frontShiny:String?)

