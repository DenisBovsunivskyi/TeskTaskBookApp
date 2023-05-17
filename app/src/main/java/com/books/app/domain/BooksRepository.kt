package com.books.app.domain

import com.books.app.core.ResponseInfo
import com.books.app.core.utils.text.UniversalText
import com.books.app.data.models.banner.TopBannersSlidesDto
import com.books.app.data.models.books.BooksDto
import kotlinx.coroutines.flow.Flow

interface BooksRepository {
    suspend fun fetchBookBannersInfo(): Flow<ResponseInfo<TopBannersSlidesDto,UniversalText>>
    suspend fun fetchMainBooks(): Flow<ResponseInfo<BooksDto,UniversalText>>
}