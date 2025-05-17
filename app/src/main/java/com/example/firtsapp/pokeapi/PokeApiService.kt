package com.example.firtsapp.pokeapi

import retrofit2.Response
import retrofit2.http.GET

interface PokeApiService {

  @GET("/")
  suspend fun getPokemons(): Response<PokemonDataResponse>
}