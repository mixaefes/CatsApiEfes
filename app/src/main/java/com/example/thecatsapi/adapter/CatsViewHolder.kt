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
    fun bind(imageUrl: String?) {
        binding.CatImageView.load(imageUrl)
        binding.root.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        Log.i("ViewHolder","Item is clicked")
        clickListener.onCatClick(absoluteAdapterPosition)
    }

}