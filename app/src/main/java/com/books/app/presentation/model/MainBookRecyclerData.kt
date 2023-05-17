package com.books.app.presentation.model

import com.books.app.data.models.banner.TopBannerSlide
import com.books.app.data.models.books.Books

data class MainBookRecyclerData(
    val id: Int,
    val type: DataItemType,
    val title: String?= null,
    val bannerList:List<TopBannerSlide>? = null,
    var bookList: List<Books>? = null
)
