package com.example.firtsapp.pokeapi

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.example.firtsapp.R
import com.example.firtsapp.databinding.ActivityPokeApiBinding
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Queue

class PokeApiActivity : AppCompatActivity() {

  private lateinit var  binding: ActivityPokeApiBinding
  private lateinit var retrofit: Retrofit
  private lateinit var pokeRetrofit: Retrofit

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    binding = ActivityPokeApiBinding.inflate(layoutInflater)
    setContentView(binding.root)
    retrofit = getRetrofit()
    pokeRetrofit = getPokeRetrofit()
    initUI()
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }
  }

  private fun initUI(){
    binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
      override fun onQueryTextSubmit(query: String?): Boolean {
        searchByName(query.orEmpty())
        //searchByPokeName()
        return false
      }

      override fun onQueryTextChange(newText: String?): Boolean {
        return  false
      }
    })
  }

  private fun searchByName(query: String){
    binding.progressBar.isVisible = true
    CoroutineScope(Dispatchers.IO).launch {
      val myResponse: Response<SuperHeroDataResponse> = retrofit.create(ApiService::class.java).getSuperheroes(query)
      if(myResponse.isSuccessful){
        Log.i("andromeda", "funciona")
        val response: SuperHeroDataResponse? = myResponse.body()
        if(response != null){
          Log.i("andromeda", response.toString())
          runOnUiThread {
            binding.progressBar.isVisible = false
          }
        }
      }else{
        Log.i("andromeda", "no funciona")
      }
    }
  }

  private fun searchByPokeName(){
    CoroutineScope(Dispatchers.IO).launch {
      val myPokemons: Response<PokemonDataResponse> = pokeRetrofit.create(PokeApiService::class.java).getPokemons()
      if(myPokemons.isSuccessful){
        Log.i("andromeda", "funciona")
        val response: PokemonDataResponse? = myPokemons.body()
        if(response != null){
          Log.i("andromeda", response.toString())
        }
      }else{
        Log.i("andromeda", "no funciona")
      }
    }
  }

  private fun getRetrofit(): Retrofit{
    return Retrofit.Builder().baseUrl("https://superheroapi.com/").addConverterFactory(
      GsonConverterFactory.create()).build()
  }

  private fun getPokeRetrofit(): Retrofit{
    return Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/pokemon/").addConverterFactory(
      GsonConverterFactory.create()).build()
  }

}