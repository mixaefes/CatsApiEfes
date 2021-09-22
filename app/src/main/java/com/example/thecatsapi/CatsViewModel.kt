package com.example.thecatsapi

import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.thecatsapi.pagination.CatsRemoteDataSourceImpl
import com.example.thecatsapi.retrofit.Cat
import com.example.thecatsapi.retrofit.CatsApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class CatsViewModel : ViewModel() {
  // private val _cats = MutableLiveData<PagingData<Cat>>()
  //  val cats: LiveData<PagingData<Cat>> get() = _cats

/*    init {
        viewModelScope.launch {
           // _cats.value = CatsApiService.getCatsService()
            _cats = CatsRemoteDataSourceImpl(CatsApiService).getAllCats()
        }*/
    val cats:Flow<PagingData<Cat>> = CatsRemoteDataSourceImpl(CatsApiService).getAllCats()

    }
/*    fun getCats(): LiveData<PagingData<Cat>> {
       return CatsRemoteDataSourceImpl(CatsApiService).getAllCats()
    }*/
//}

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CatsViewModel::class.java)) {
            return CatsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}