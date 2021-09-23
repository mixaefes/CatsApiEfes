package com.example.thecatsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thecatsapi.adapter.CatsViewAdapter
import com.example.thecatsapi.adapter.OnItemClickListener
import com.example.thecatsapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        openListFragment()

    }

    private fun openListFragment() {
        val listFragment = CatsListFragment.newInstance()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,listFragment)
        transaction.commit()
    }

    companion object {
        private const val LOG_TAG = "MainActivity"
    }

/*    override fun onCatClick(position: Int) {
        Log.i(LOG_TAG,"Item was clicked position is: $position")
    }*/
}