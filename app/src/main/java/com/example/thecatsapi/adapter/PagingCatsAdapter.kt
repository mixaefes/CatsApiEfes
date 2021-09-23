package com.example.thecatsapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.thecatsapi.databinding.CatItemBinding
import com.example.thecatsapi.retrofit.Cat
import javax.security.auth.callback.Callback

class PagingCatsAdapter(
    private val onItemClickListener: OnItemClickListener
) :
    PagingDataAdapter<Cat, CatsViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
        holder.bind(getItem(position)?.url)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CatItemBinding.inflate(layoutInflater,parent,false)
        return CatsViewHolder(binding,onItemClickListener)
    }
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Cat>(){
            override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
                return oldItem == newItem
            }

        }
    }
}