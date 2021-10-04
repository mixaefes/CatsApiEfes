package com.example.thecatsapi
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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
        transaction.setCustomAnimations(
            R.anim.card_flip_right_in, R.anim.card_flip_right_out,
            R.anim.card_flip_left_in, R.anim.card_flip_left_out
        )
        transaction.replace(
            R.id.container, listFragment
        )
        transaction.commit()
    }
    private fun openAboutCatFragment(id: String, imageUrl: String) {
        val aboutCatFragment = AboutCatFragment.newInstance(id, imageUrl)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.card_flip_right_in, R.anim.card_flip_right_out,
            R.anim.card_flip_left_in, R.anim.card_flip_left_out
        )
        transaction.replace(R.id.container, aboutCatFragment)
        transaction.addToBackStack("cat_fragment")
        transaction.commit()
    }
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
    companion object {
        private const val LOG_TAG = "MainActivity"
    }
    override fun showCatInSecondFragment(position: Int, itemId: String, imageUrl: String) {
        Log.i(LOG_TAG, "Item was clicked position is: $position,id  = $itemId, url:$imageUrl")
        openAboutCatFragment(itemId, imageUrl)
    }
}
