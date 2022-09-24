package com.marcsogasdev.pokedexkotlin

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
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
        initRecyclerView()

    }

    private fun initRecyclerView() {
        //ponemos el recycler con 3 columnas


        adapter = PokemonAdapter(pokeimage)
        binding.recyclerView.layoutManager=GridLayoutManager(this,3)
        binding.recyclerView.adapter=adapter

    }

    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
               // "https://pokeapi.co/api/v2/pokemon/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByName(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call =
                getRetrofit().create(PokeapiService::class.java).obtenerListaPokemon("$query/images")
            val pokemons: PokemonRespuesta? = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    showCall()
                   val images:List<String> = pokemons?.images ?: emptyList()
                    pokeimage.clear()
                    pokeimage.addAll(images)
                    adapter.notifyDataSetChanged()
                    //show

                } else {
                    showError()
                }
                hideKeyboard()
            }

        }

    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)

    }
    private fun showCall() {
        Toast.makeText(this,"Call correcta",Toast.LENGTH_SHORT).show()
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
