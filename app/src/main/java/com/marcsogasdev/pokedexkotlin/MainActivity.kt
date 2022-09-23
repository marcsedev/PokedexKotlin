package com.marcsogasdev.pokedexkotlin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcsogasdev.pokedexkotlin.databinding.ActivityMainBinding

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(),SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PokemonAdapter
    private val pokeimage = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.svDogs.setOnQueryTextListener(this)
        //initRecyclerView()

    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        binding.recyclerView.adapter=adapter

    }

    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/pokemon/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByName(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call =
                getRetrofit().create(PokeapiService::class.java).obtenerListaPokemon("$query/id")
            val pokemons: PokemonRespuesta? = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    val images:List<String> = pokemons?.images ?: emptyList()
                    pokeimage.clear()
                    pokeimage.addAll(images)
                    adapter.notifyDataSetChanged()

                    //show

                } else {
                    showError()

                }
            }
        }
    }

    private fun showError() {
        Toast.makeText(this,"Ha ocurrido un error",Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            searchByName(query.toLowerCase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }


}


    /*
    private var retrofit: Retrofit? = null
    private var recyclerView: RecyclerView? = null
    private var listaPokemonAdapter: ListaPokemonAdapter? = null
    private var offset = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        listaPokemonAdapter = ListaPokemonAdapter(this)
        recyclerView!!.setAdapter(listaPokemonAdapter)
        recyclerView!!.setHasFixedSize(true)
        //ponemos el recycler con 3 columnas
        val layoutManager = GridLayoutManager(this, 3)
        recyclerView!!.layoutManager = layoutManager

        //saber cuando llegamos al final del recyclerview

        //recyclerView
        retrofit = Retrofit.Builder() //crear instancia
            .baseUrl("https://pokeapi.co/api/v2/") //url base donde se obtienen os datos
            .addConverterFactory(GsonConverterFactory.create()) // devuelve la info en objetos
            .build()
        offset = 0
        obtenerDatos(offset)
    }

    private fun obtenerDatos(offset: Int) {
        //usamos la interfaz
        val service: PokeapiService = retrofit!!.create(PokeapiService::class.java)
        val gen1 = 151
        val pokemonRespuestaCall: Call<PokemonRespuesta> = service.obtenerListaPokemon(gen1, offset)
        pokemonRespuestaCall.enqueue(object : Callback<PokemonRespuesta?> {
            override fun onResponse(
                call: Call<PokemonRespuesta?>,
                response: Response<PokemonRespuesta?>
            ) {
                if (response.isSuccessful()) {
                    val pokemonRespuesta: PokemonRespuesta? = response.body()
                    //comprobar la consulta
                    //guardamos en array list
                    val listaPokemon: ArrayList<Pokemon?>? = pokemonRespuesta?.getResults()

                    //mandamos el arraylist de pokemon
                    listaPokemonAdapter!!.adicionarListaPokemon(listaPokemon)


                    *//*              //recorremos array list y mostramos la consulta por consola
                            for (int i =0; i<listaPokemon.size(); i++){
                                //recorremos cada p es un pokemon
                                Pokemon p=listaPokemon.get(i);
                                // se muestra por consola
                                Log.i(TAG," Pokemon: " + p.getName());

                            }*//*
                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody())
                }
            }

            override fun onFailure(call: Call<PokemonRespuesta?>, t: Throwable) {
                Log.e(TAG, " onFailure: " + t.message)
            }
        })
    }

    companion object {
        private const val TAG = "POKEDEX"
    }
}*/