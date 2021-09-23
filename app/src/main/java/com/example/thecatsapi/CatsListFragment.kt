package com.example.thecatsapi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.example.thecatsapi.adapter.CatsViewAdapter
import com.example.thecatsapi.adapter.OnItemClickListener
import com.example.thecatsapi.adapter.PagingCatsAdapter
import com.example.thecatsapi.databinding.FragmentCatsListBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match


class CatsListFragment : Fragment(),OnItemClickListener {
    private var _binding: FragmentCatsListBinding? = null
    private val binding get() = _binding!!
    private val catViewModel: CatsViewModel by viewModels {
        ViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
/*
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
*/
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCatsListBinding.inflate(inflater, container, false)

      //  val myAdapter = CatsViewAdapter()
        val myAdapter = PagingCatsAdapter(this)
        binding.CatsRecyclerId.layoutManager = StaggeredGridLayoutManager(2,VERTICAL)
        binding.CatsRecyclerId.adapter = myAdapter
/*        catViewModel.cats.observe(this.viewLifecycleOwner) {
            Log.i(LOG_TAG, "these are my cats: $it")
            myAdapter.submitData(it)
        }*/

         lifecycleScope.launch {
             catViewModel.cats.collectLatest { it ->
                 myAdapter.submitData(it)
             }
         }
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = CatsListFragment()

        private const val LOG_TAG = "ListFragment"

    }

    override fun onCatClick(position: Int) {
        Log.i(LOG_TAG,"this is item position $position")
    }

}