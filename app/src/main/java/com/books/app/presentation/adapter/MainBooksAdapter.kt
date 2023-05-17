package com.books.app.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.books.app.R
import com.books.app.data.models.banner.TopBannerSlide
import com.books.app.data.models.books.Books
import com.books.app.databinding.ItemBannerBinding
import com.books.app.databinding.ItemHorizontalRecyclerBinding
import com.books.app.presentation.model.DataItemType
import com.books.app.presentation.model.MainBookRecyclerData

class MainBooksAdapter :
    ListAdapter<MainBookRecyclerData, MainBooksViewHolder>(BookMainDiffCallBack()) {

    var itemBooksClickListener: ((item: Books) -> Unit)? = null
    var itemBannerClickListener: ((item: TopBannerSlide) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainBooksViewHolder {
        return when (viewType) {
            R.layout.item_banner -> MainBooksViewHolder.BannerViewHolder(
                ItemBannerBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            R.layout.item_horizontal_recycler -> MainBooksViewHolder.HorizontalViewHolder(
                ItemHorizontalRecyclerBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: MainBooksViewHolder, position: Int) {

        holder.itemBookClickListener = itemBooksClickListener
        holder.itemBannerSildeClockListener = itemBannerClickListener

        val item = getItem(position)
        when (holder) {
            is MainBooksViewHolder.BannerViewHolder -> holder.bind(item as MainBookRecyclerData)
            is MainBooksViewHolder.HorizontalViewHolder -> holder.bind(item as MainBookRecyclerData)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).type) {
            DataItemType.HORIZONTAL_LIST ->
                R.layout.item_horizontal_recycler

            else ->
                R.layout.item_banner
        }
    }

    fun setBookClickListener(listener: (Books) -> Unit) {
        itemBooksClickListener = listener
    }

    fun setBannerClickListener(listener: (TopBannerSlide) -> Unit) {
        itemBannerClickListener = listener
    }

    class BookMainDiffCallBack : DiffUtil.ItemCallback<MainBookRecyclerData>() {

        override fun areItemsTheSame(
            oldItem: MainBookRecyclerData,
            newItem: MainBookRecyclerData
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MainBookRecyclerData,
            newItem: MainBookRecyclerData
        ): Boolean {
            return oldItem == newItem
        }
    }

}