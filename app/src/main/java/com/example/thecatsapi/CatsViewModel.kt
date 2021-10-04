package com.example.thecatsapi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingData
import com.example.thecatsapi.pagination.CatsRemoteDataSourceImpl
import com.example.thecatsapi.retrofit.Cat
import com.example.thecatsapi.retrofit.CatsApiService
import kotlinx.coroutines.flow.Flow

class CatsViewModel : ViewModel() {
    val cats: Flow<PagingData<Cat>> = CatsRemoteDataSourceImpl(CatsApiService).getAllCats()
}
class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CatsViewModel::class.java)) {
            return CatsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
