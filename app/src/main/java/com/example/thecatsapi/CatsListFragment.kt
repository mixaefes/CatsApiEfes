package com.example.thecatsapi

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.example.thecatsapi.adapter.OnItemClickListener
import com.example.thecatsapi.adapter.PagingCatsAdapter
import com.example.thecatsapi.databinding.FragmentCatsListBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CatsListFragment : Fragment(), OnItemClickListener {
    private var _binding: FragmentCatsListBinding? = null
    private val binding get() = _binding!!
    private val catViewModel: CatsViewModel by viewModels {
        ViewModelFactory()
    }
    var listener: ShowMyCatListener? = null
    private var myAdapter: PagingCatsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myAdapter = PagingCatsAdapter(this)
        lifecycleScope.launch {
            catViewModel.cats.collectLatest { it ->
                myAdapter?.submitData(it)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as ShowMyCatListener
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCatsListBinding.inflate(inflater, container, false)
        binding.CatsRecyclerId.layoutManager = StaggeredGridLayoutManager(2, VERTICAL)
        binding.CatsRecyclerId.adapter = myAdapter
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

    override fun onCatClick(position: Int, itemId: String, imageUrl: String) {
        Log.i(LOG_TAG, "this is item position $position")
        listener?.showCatInSecondFragment(position, itemId, imageUrl)
    }
}
