package com.books.app.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.asksira.loopingviewpager.LoopingPagerAdapter
import com.books.app.R
import com.books.app.data.models.banner.TopBannerSlide
import com.books.app.presentation.module.GlideApp
import com.bumptech.glide.load.engine.DiskCacheStrategy

class BannerLoopingImageSlideAdapter(
    itemList: List<TopBannerSlide>,
    isInfinite: Boolean
) : LoopingPagerAdapter<TopBannerSlide>(itemList, isInfinite) {

    //This method will be triggered if the item View has not been inflated before.
    override fun inflateView(
        viewType: Int,
        container: ViewGroup,
        listPosition: Int
    ): View {
        return LayoutInflater.from(container.context)
            .inflate(R.layout.item_banner_card_image, container, false)
    }

    override fun bindView(
        convertView: View,
        listPosition: Int,
        viewType: Int
    ) {
        val imageView = convertView.findViewById<ImageView>(R.id.iv_images)
        GlideApp.with(imageView).load(itemList?.get(listPosition)?.cover)
            .diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView)
        convertView.setOnClickListener {
            itemList?.get(listPosition)?.let { item -> onClickListener?.invoke(item) }
        }

    }

    private var onClickListener: ((TopBannerSlide) -> Unit)? = null

    fun setOnClickListener(listener: (TopBannerSlide) -> Unit) {
        onClickListener = listener
    }
}