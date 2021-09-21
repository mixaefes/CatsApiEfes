package com.example.thecatsapi.retrofit

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface CatsApi {
    //api_key:452cb488-7166-416d-859a-31115818696f
    @GET("/v1/images/search?api_key:452cb488-7166-416d-859a-31115818696f&limit=10&page=0&order=DESC")
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