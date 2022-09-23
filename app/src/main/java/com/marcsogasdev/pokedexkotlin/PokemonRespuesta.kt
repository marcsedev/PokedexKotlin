package com.marcsogasdev.pokedexkotlin

import com.google.gson.annotations.SerializedName

data class PokemonRespuesta (
    @SerializedName("status")var status:String,
    @SerializedName("message") var images:List<String>) {

    /*@SerializedName ("Num") var id:Int,
                         @SerializedName ("Name") var name:String,
                         @SerializedName ("Image") var sprites:List<String>){*/
    //atributos que nos interesan del GSON
    //array del resultado

}