package com.example.thecatsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thecatsapi.adapter.CatsViewAdapter
import com.example.thecatsapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val catViewModel: CatsViewModel by viewModels {
        ViewModelFactory()
    }
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val myAdapter = CatsViewAdapter()
        binding.CatsRecyclerId.layoutManager = LinearLayoutManager(this)
        binding.CatsRecyclerId.adapter = myAdapter
        catViewModel.cats.observe(this) {
            Log.i(LOG_TAG, "these are my cats: $it")
            myAdapter.submitList(it)
        }


    }

    companion object {
        private const val LOG_TAG = "MainActivity"
    }
}