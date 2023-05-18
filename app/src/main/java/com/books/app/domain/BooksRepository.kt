package com.books.app.domain

import com.books.app.core.ResponseInfo
import com.books.app.core.utils.text.UniversalText
import com.books.app.data.models.banner.TopBannerSlide
import com.books.app.data.models.books.Books
import kotlinx.coroutines.flow.Flow

interface BooksRepository {
    suspend fun fetchBookBannersInfo(): Flow<ResponseInfo<List<TopBannerSlide>,UniversalText>>
    suspend fun fetchMainBooks(): Flow<ResponseInfo<List<Books>,UniversalText>>
    suspend fun fetchDetailsBooks(): Flow<ResponseInfo<List<Books>,UniversalText>>
    suspend fun fetchRecommendedBooks(): Flow<ResponseInfo<List<Int>,UniversalText>>
}