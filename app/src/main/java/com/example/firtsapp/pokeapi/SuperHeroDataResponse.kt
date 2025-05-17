package com.example.firtsapp.pokeapi

import com.google.gson.annotations.SerializedName

data class SuperHeroDataResponse(@SerializedName("response") val response: String, @SerializedName("results") val results: List<SuperHeroItemResponse>)

data class SuperHeroItemResponse(@SerializedName("id") val id: String, @SerializedName("name") val name: String)

data class PokemonDataResponse(@SerializedName("results") val id: List<PokemonItemRespone>)

data class PokemonItemRespone(@SerializedName("name") val name: String, @SerializedName("url") val url: String)