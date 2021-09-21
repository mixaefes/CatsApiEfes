package com.example.thecatsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {
    val catViewModel:CatsViewModel by viewModels {
        ViewModelFactory()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
catViewModel.cats.observe(this){
    Log.i(LOG_TAG, "these are my cats: $it")
}
    }
    companion object{
        private const val LOG_TAG = "MainActivity"
    }
}