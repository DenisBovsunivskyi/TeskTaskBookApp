package com.books.app.data.models.banner


import com.google.gson.annotations.SerializedName

data class TopBannerSlide(
    @SerializedName("book_id")
    val bookId: Int,
    @SerializedName("cover")
    val cover: String,
    @SerializedName("id")
    val id: Int
)