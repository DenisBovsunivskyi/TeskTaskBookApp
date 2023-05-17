package com.books.app.domain.usecase

import com.books.app.domain.BooksRepository

class FetchBannerInfoUseCase(private val bookRepository: BooksRepository) {
    suspend fun execute() = bookRepository.fetchBookBannersInfo()
}