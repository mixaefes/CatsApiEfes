package com.example.thecatsapi

import android.app.DownloadManager
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import coil.api.load
import com.example.thecatsapi.databinding.FragmentAboutCatBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class AboutCatFragment : Fragment() {
    private var _binding: FragmentAboutCatBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var pickedItemId: String? = null
    private var pickedImageUrl: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pickedItemId = it.getString(PARAM_ID)
            pickedImageUrl = it.getString(PARAM_URL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutCatBinding.inflate(inflater, container, false)
        binding.DownloadButton.setImageResource(R.drawable.ic_baseline_cloud_download_24)
        binding.pickedCatimageView.load(
            pickedImageUrl
        )
        binding.DownloadButton.setOnClickListener {
            downloadImage(pickedImageUrl)
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun downloadImage(url: String?) {
        url?.let {
            val directory = File(Environment.DIRECTORY_PICTURES)
            if (!directory.exists()) {
                directory.mkdirs()
            }
            val downloadManager =
                context?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val downloadUri = Uri.parse(url)
            val request = DownloadManager.Request(downloadUri).apply {
                setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                setTitle(url.substring(url.lastIndexOf('/') + 1))
                setDescription("from Cat api")
                setDestinationInExternalPublicDir(
                    directory.toString(),
                    url.substring(url.lastIndexOf('/') + 1)
                )
            }
            val downloadId = downloadManager.enqueue(request)
            val query = DownloadManager.Query().setFilterById(downloadId)
            CoroutineScope(Dispatchers.IO).launch {
                var downloading = true
                while (downloading) {
                    val cursor: Cursor = downloadManager.query(query)
                    cursor.moveToFirst()
                    if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                        downloading = false
                        withContext(Dispatchers.Main) {
                            Toast.makeText(context, "image downloaded", Toast.LENGTH_SHORT).show()
                            binding.DownloadButton.setImageResource(R.drawable.ic_baseline_check_24)
                        }
                    }
                    cursor.close()
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(id: String, imageUrl: String) =
            AboutCatFragment().apply {
                arguments = Bundle().apply {
                    putString(PARAM_ID, id)
                    putString(PARAM_URL, imageUrl)
                }
            }

        private const val LOG_TAG = "AboutCatFragment"
        private const val PARAM_ID = "id"
        private const val PARAM_URL = "image_url"
    }
}
