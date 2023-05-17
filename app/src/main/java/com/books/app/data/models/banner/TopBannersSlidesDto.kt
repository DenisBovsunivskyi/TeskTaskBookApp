package com.books.app.data.models.banner


import com.google.gson.annotations.SerializedName

data class TopBannersSlidesDto(
    @SerializedName("top_banner_slides")
    val topBannerSlides: List<TopBannerSlide>
)