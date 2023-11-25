package com.hackathon.zero.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hackathon.zero.data.Product
import com.hackathon.zero.data.ProductSearchItem
import com.hackathon.zero.databinding.ItemCalorieCardBinding
import com.hackathon.zero.databinding.ItemProductSearchBinding

class SearchRVAdapter() :
    RecyclerView.Adapter<SearchRVAdapter.ProductItemHolder>() {

    private var list: List<ProductSearchItem?> = listOf()

    inner class ProductItemHolder(
        private val binding: ItemProductSearchBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ProductSearchItem) {
            binding.item = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemHolder =
        ProductItemHolder(
            ItemProductSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ProductItemHolder, position: Int) {
        holder.bind(list[position]!!)
    }

    fun setList(newList: List<ProductSearchItem?>) {
        list = newList
        notifyDataSetChanged()
    }
}