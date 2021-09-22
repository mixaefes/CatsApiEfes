package com.example.thecatsapi.pagination

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.thecatsapi.retrofit.Cat
import kotlinx.coroutines.flow.Flow

interface CatsRemoteDataSource {
    fun getAllCats(): Flow<PagingData<Cat>>
}