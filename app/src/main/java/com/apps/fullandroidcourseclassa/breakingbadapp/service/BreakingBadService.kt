package com.apps.fullandroidcourseclassa.breakingbadapp.service

import com.apps.fullandroidcourseclassa.breakingbadapp.model.BBCharacter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


const val BASE_URL = "https://www.breakingbadapi.com/api/"
interface BreakingBadService {
    @GET("characters")
    suspend fun getCharacters(): List<BBCharacter>
}

object BreakingBadNetwork {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val serviceApi = retrofit.create(BreakingBadService::class.java)
}
