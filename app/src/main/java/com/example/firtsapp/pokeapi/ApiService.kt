package com.example.firtsapp.pokeapi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

  @GET("/api/a04dc53222763c564388a0767e82c926/search/{name}")
  suspend fun getSuperheroes(@Path("name") superheroName: String): Response<SuperHeroDataResponse>

}