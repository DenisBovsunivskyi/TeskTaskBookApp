package com.books.app.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.books.app.data.models.books.Books
import com.books.app.databinding.ItemHorizontalDetailsBookBinding


class HorizontalBookDetailsAdapter :
    RecyclerView.Adapter<HorizontalBookDetailsAdapter.BookViewHolder>() {
    private val callback = object : DiffUtil.ItemCallback<Books>() {
        override fun areItemsTheSame(
            oldItem: Books,
            newItem: Books
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Books,
            newItem: Books
        ): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, callback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding =
            ItemHorizontalDetailsBookBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return BookViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = differ.currentList[position]
        holder.bind(book)
    }

    inner class BookViewHolder(val binding: ItemHorizontalDetailsBookBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Books) {
            binding.model = book
        }
    }


}
