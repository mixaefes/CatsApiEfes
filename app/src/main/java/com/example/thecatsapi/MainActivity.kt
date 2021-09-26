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

class MainActivity : AppCompatActivity(), ShowMyCatListener {

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
        transaction.replace(R.id.container, listFragment)
        transaction.commit()
    }

    private fun openAboutCatFragment(id:String,imageUrl: String) {
        val aboutCatFragment = AboutCatFragment.newInstance(id,imageUrl)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, aboutCatFragment)
        transaction.addToBackStack("cat_fragment")
        transaction.commit()
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount>0){
            supportFragmentManager.popBackStack()
        }else{
            super.onBackPressed()
        }

    }

    companion object {
        private const val LOG_TAG = "MainActivity"
    }


    override fun showCatInSecondFragment(position: Int,itemId:String,imageUrl:String) {
        Log.i(LOG_TAG, "Item was clicked position is: $position,id  = $itemId, url:$imageUrl")
        openAboutCatFragment(itemId,imageUrl)
    }
}