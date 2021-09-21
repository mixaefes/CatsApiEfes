package com.example.thecatsapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.thecatsapi.databinding.CatItemBinding
import com.example.thecatsapi.retrofit.Cat


class CatsViewAdapter : ListAdapter<Cat, CatsViewHolder>(itemComparator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CatItemBinding.inflate(layoutInflater,parent,false)
        return CatsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
        holder.bind(getItem(position).url)
    }
    companion object{
        val itemComparator = object:DiffUtil.ItemCallback<Cat>(){
            override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
               return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
                return oldItem.url == newItem.url
            }

        }
    }
}