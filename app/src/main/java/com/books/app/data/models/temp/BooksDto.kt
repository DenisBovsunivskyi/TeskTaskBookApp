package com.books.app.data.models.temp


import com.books.app.data.models.banner.TopBannerSlide
import com.books.app.data.models.books.Books
import com.google.gson.annotations.SerializedName

data class BooksDto(
    @SerializedName("books")
    val books: List<Books>,
    @SerializedName("top_banner_slides")
    val topBannerSlides: List<TopBannerSlide>,
    @SerializedName("you_will_like_section")
    val youWillLikeSection: List<Int>
)