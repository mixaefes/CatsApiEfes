package com.example.thecatsapi.retrofit

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface CatsApi {
    @GET("/v1/images/search?limit=3&page=100&order=DESC")
    suspend fun getCats(): List<Cat>
}

object CatsApiService {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://api.thecatapi.com")
        .build()
    private val catsService = retrofit.create(CatsApi::class.java)
    suspend fun getCatsService(): List<Cat> {
        return withContext(Dispatchers.IO) {
            catsService.getCats()
        }
    }
}