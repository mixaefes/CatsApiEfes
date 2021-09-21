package com.example.thecatsapi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thecatsapi.adapter.CatsViewAdapter
import com.example.thecatsapi.databinding.FragmentCatsListBinding

// TODO: Rename parameter arguments, choose names that match


class CatsListFragment : Fragment() {
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

        val myAdapter = CatsViewAdapter()
        binding.CatsRecyclerId.layoutManager = LinearLayoutManager(activity)
        binding.CatsRecyclerId.adapter = myAdapter
        catViewModel.cats.observe(this.viewLifecycleOwner) {
            Log.i(LOG_TAG, "these are my cats: $it")
            myAdapter.submitList(it)
        }
        return binding.root

    }

    companion object {
        @JvmStatic
        fun newInstance() = CatsListFragment()

        private const val LOG_TAG = "ListFragment"

    }

}