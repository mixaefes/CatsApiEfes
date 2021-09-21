package com.example.thecatsapi.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.thecatsapi.databinding.CatItemBinding

class CatsViewHolder(private val binding: CatItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(imageUrl:String?) {
        binding.CatImageView.load(imageUrl)
    }
}