package com.books.app.presentation.adapter

import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.books.app.R
import com.books.app.data.models.books.Books
import com.books.app.databinding.ItemBannerBinding
import com.books.app.databinding.ItemHorizontalRecyclerBinding
import com.books.app.presentation.model.MainBookRecyclerData
import com.books.app.presentation.util.MarginHorizontalItemDecoration

sealed class MainBooksViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    var itemBookClickListener: ((item: Books) -> Unit)? = null

    class BannerViewHolder(
        private val binding: ItemBannerBinding,
    ) :
        MainBooksViewHolder(binding) {

        init {
            binding.bannerViewPager.clipChildren = false
            binding.bannerViewPager.clipToPadding = false
            binding.bannerViewPager.offscreenPageLimit = 3
        }

        fun bind(mainData: MainBookRecyclerData) {
            mainData.bannerList?.let {
                val childAdapter = BannerLoopingImageSlideAdapter(mainData.bannerList, true)
                binding.bannerViewPager.adapter = childAdapter
                //Custom bind indicator
                binding.tabIndicator.highlighterViewDelegate = {
                    val itemView = CardView(it.context)
                    itemView.layoutParams = ViewGroup.LayoutParams(
                        it.context.resources.getDimension(R.dimen.custom_indicator_size).toInt(),
                        it.context.resources.getDimension(R.dimen.custom_indicator_size).toInt()
                    )
                    itemView.radius = 50F
                    itemView.setCardBackgroundColor(
                        ContextCompat.getColor(
                            it.context,
                            R.color.pink
                        )
                    )

                    itemView
                }
                binding.tabIndicator.unselectedViewDelegate = {
                    val itemView = CardView(it.context)

                    itemView.layoutParams = ViewGroup.LayoutParams(
                        it.context.resources.getDimension(R.dimen.custom_indicator_size).toInt(),
                        it.context.resources.getDimension(R.dimen.custom_indicator_size).toInt()
                    )
                    itemView.radius = 50F
                    itemView.setCardBackgroundColor(
                        ContextCompat.getColor(
                            it.context,
                            R.color.white_70_transparent
                        )
                    )

                    itemView
                }
                binding.bannerViewPager.onIndicatorProgress = { selectingPosition, progress ->
                    binding.tabIndicator.onPageScrolled(
                        selectingPosition,
                        progress
                    )
                }
                binding.tabIndicator.updateIndicatorCounts(binding.bannerViewPager.indicatorCount)

            }
        }
    }

    class HorizontalViewHolder(private val binding: ItemHorizontalRecyclerBinding) :
        MainBooksViewHolder(binding) {
        private val childAdapter by lazy { HorizontalBookAdapter() }

        init {
            binding.horizontalRv.addItemDecoration(
                MarginHorizontalItemDecoration(
                    rightSize = binding.horizontalRv.context.resources.getDimensionPixelSize(
                        R.dimen.recycler_margin_sides
                    ),
                    leftSize = binding.horizontalRv.context.resources.getDimensionPixelSize(
                        R.dimen.recycler_margin_sides
                    )
                )
            )
            binding.horizontalRv.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            binding.horizontalRv.adapter = childAdapter
        }

        fun bind(booksData: MainBookRecyclerData) {
            binding.model = booksData
            childAdapter.differ.submitList(booksData.bookList)
            childAdapter.setOnClickListener {
                itemBookClickListener?.invoke(it)
            }
        }
    }

}