package com.example.thecatsapi

import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import com.example.thecatsapi.retrofit.Cat
import com.example.thecatsapi.retrofit.CatsApiService
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class CatsViewModel : ViewModel() {
    private val _cats = MutableLiveData<List<Cat>>()
    val cats: LiveData<List<Cat>> get() = _cats

    init {
        viewModelScope.launch {
            _cats.value = CatsApiService.getCatsService()
        }

    }
}

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CatsViewModel::class.java)) {
            return CatsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}