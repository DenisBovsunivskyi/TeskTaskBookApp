package com.books.app.presentation.binding_adapters

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.books.app.presentation.module.GlideApp
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop



object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("setHorizontalBookImage")
    fun bindingSetHorizontalBookImage(view: AppCompatImageView, path: String?) {
        GlideApp.with(view)
            .asDrawable()
            .load(path)
            .transform(CenterCrop())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(view)
    }


}
