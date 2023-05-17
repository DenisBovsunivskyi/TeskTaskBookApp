package com.books.app.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.books.app.data.models.books.Books
import com.books.app.databinding.ItemHorizontalBookBinding


class HorizontalBookAdapter : RecyclerView.Adapter<HorizontalBookAdapter.BookViewHolder>() {
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
            ItemHorizontalBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = differ.currentList[position]
        holder.bind(book)
    }

    inner class BookViewHolder(val binding: ItemHorizontalBookBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Books) {
            binding.model = book
            binding.root.setOnClickListener {
                onClickListener?.let {
                    it(book)
                }
            }
        }
    }

    private var onClickListener: ((Books) -> Unit)? = null

    fun setOnClickListener(listener: (Books) -> Unit) {
        onClickListener = listener
    }

}
