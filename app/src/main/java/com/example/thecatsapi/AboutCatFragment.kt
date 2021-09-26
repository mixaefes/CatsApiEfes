package com.example.thecatsapi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.api.load
import com.example.thecatsapi.databinding.FragmentAboutCatBinding
import com.example.thecatsapi.retrofit.Cat
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val PARAM_ID = "id"
private const val PARAM_URL = "image_url"

/**
 * A simple [Fragment] subclass.
 * Use the [AboutCatFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AboutCatFragment : Fragment() {
    private var _binding: FragmentAboutCatBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    private var pickedItemId: String? = null
    private var pickedImageUrl: String? = null
    private val myViewModel: CatsViewModel by viewModels {
        ViewModelFactory()
    }
    private var myCat: Cat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pickedItemId = it.getString(PARAM_ID)
            pickedImageUrl = it.getString(PARAM_URL)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutCatBinding.inflate(inflater,container,false)
        lifecycleScope.launch {
            myCat = myViewModel.getPickedCat(pickedItemId!!)
        }
        Log.i(LOG_TAG, "this is my picked Cat: $myCat")
        binding.pickedCatimageView.load(pickedImageUrl)
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param id Parameter 1.
         * @return A new instance of fragment AboutCatFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(id: String, imageUrl: String) =
            AboutCatFragment().apply {
                arguments = Bundle().apply {
                    putString(PARAM_ID, id)
                    putString(PARAM_URL, imageUrl)
                }
            }

        private const val LOG_TAG = "AboutCatFragment"
    }
}