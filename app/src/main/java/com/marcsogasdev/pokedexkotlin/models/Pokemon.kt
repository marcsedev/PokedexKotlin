package com.marcsogasdev.pokedexkotlin.models

public class Pokemon {

    private var number = 0
    private var name: String? = null
    private var url: String? = null

      fun getNumber(): Int {
        //la api no devuelve el numero por lo que tenemos que extraerlo del aurl
        // dividimos la url
        val urlPartes = url!!.split("/".toRegex()).toTypedArray()
        //accedemos a la última posición que es el número
        return urlPartes[urlPartes.size - 1].toInt()
    }
}