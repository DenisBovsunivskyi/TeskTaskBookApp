package com.books.app.data.repository

import com.books.app.core.ResponseInfo
import com.books.app.core.utils.text.UniversalText
import com.books.app.data.models.banner.TopBannerSlide
import com.books.app.data.models.banner.TopBannersSlidesDto
import com.books.app.data.models.books.Book
import com.books.app.data.models.books.BooksDto
import com.books.app.domain.BooksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BooksRepositoryImpl() : BooksRepository {
    override suspend fun fetchBookBannersInfo(): Flow<ResponseInfo<TopBannersSlidesDto, UniversalText>> =
        flow {
            emit(
                ResponseInfo.Success(
                    TopBannersSlidesDto(
                        listOf(
                            TopBannerSlide(0, "123", 1),
                            TopBannerSlide(2, "123", 3),
                            TopBannerSlide(3, "123", 4)
                        )
                    )
                )
            )
        }

    override suspend fun fetchMainBooks(): Flow<ResponseInfo<BooksDto, UniversalText>> = flow {
        emit(
            ResponseInfo.Success(
                BooksDto(
                    listOf(
                        Book(
                            author = "Abc",
                            coverUrl = "asdas",
                            genre = "111",
                            id = 1,
                            likes = "123",
                            name = "das",
                            quotes = "sdas",
                            summary = "sasdas",
                            views = "123"
                        ),

                        Book(
                            author = "Abc",
                            coverUrl = "asdas",
                            genre = "111",
                            id = 1,
                            likes = "123",
                            name = "das",
                            quotes = "sdas",
                            summary = "sasdas",
                            views = "123"
                        ),
                        Book(
                            author = "Abc",
                            coverUrl = "asdas",
                            genre = "111",
                            id = 1,
                            likes = "123",
                            name = "das",
                            quotes = "sdas",
                            summary = "sasdas",
                            views = "123"
                        )
                    )
                )
            )
        )
    }

}