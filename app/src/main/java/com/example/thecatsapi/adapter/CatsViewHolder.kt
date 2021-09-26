package com.example.thecatsapi.adapter

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.thecatsapi.databinding.CatItemBinding

class CatsViewHolder(
    private val binding: CatItemBinding,
    private val clickListener: OnItemClickListener

) : RecyclerView.ViewHolder(binding.root),
    View.OnClickListener {
    private lateinit var itemId: String
    private lateinit var imageUrl: String
    fun bind(imageUrl: String?) {
        binding.CatImageView.load(imageUrl)
        binding.root.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        Log.i("ViewHolder", "Item is clicked")
        clickListener.onCatClick(absoluteAdapterPosition,itemId,imageUrl)
    }

    fun setItemIdAndImage(_id: String,_imageUrl: String) {
        imageUrl = _imageUrl
        itemId = _id
    }

}