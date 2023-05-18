package com.books.app.data.repository

import com.books.app.core.ResponseInfo
import com.books.app.core.utils.text.UniversalText
import com.books.app.data.data_source.BookDataSource
import com.books.app.data.models.banner.TopBannerSlide
import com.books.app.data.models.books.Books
import com.books.app.domain.BooksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BooksRepositoryImpl(private val bookDataSource: BookDataSource) : BooksRepository {
    override suspend fun fetchBookBannersInfo(): Flow<ResponseInfo<List<TopBannerSlide>, UniversalText>> =
        flow {
            val topBannerSlide = bookDataSource.fetchBooksMainInfo().topBannerSlides
            if (topBannerSlide.isNotEmpty()) {
                emit(
                    ResponseInfo.Success(
                        topBannerSlide
                    )
                )
            } else {
                emit(
                    ResponseInfo.Error(
                        UniversalText.Dynamic("Something went wrong")
                    )
                )
            }

        }

    override suspend fun fetchMainBooks(): Flow<ResponseInfo<List<Books>, UniversalText>> = flow {
        val books = bookDataSource.fetchBooksMainInfo().books
        if (books.isNotEmpty()) {
            emit(
                ResponseInfo.Success(
                    books
                )
            )
        } else {
            emit(
                ResponseInfo.Error(
                    UniversalText.Dynamic("Something went wrong")
                )
            )
        }
    }

    override suspend fun fetchDetailsBooks(): Flow<ResponseInfo<List<Books>, UniversalText>> = flow {
        val books = bookDataSource.fetchDetailsInfo().books
        if (books.isNotEmpty()) {
            emit(
                ResponseInfo.Success(
                    books
                )
            )
        } else {
            emit(
                ResponseInfo.Error(
                    UniversalText.Dynamic("Something went wrong")
                )
            )
        }
    }

    override suspend fun fetchRecommendedBooks(): Flow<ResponseInfo<List<Int>, UniversalText>>  = flow {
        val ids = bookDataSource.fetchBooksMainInfo().youWillLikeSection
        if (ids.isNotEmpty()) {
            emit(
                ResponseInfo.Success(
                    ids
                )
            )
        } else {
            emit(
                ResponseInfo.Error(
                    UniversalText.Dynamic("Something went wrong")
                )
            )
        }
    }
}