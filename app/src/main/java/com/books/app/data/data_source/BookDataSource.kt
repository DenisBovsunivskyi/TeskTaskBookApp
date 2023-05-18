package com.books.app.data.data_source

import com.books.app.data.models.temp.BooksDto

interface BookDataSource {
    suspend fun fetchBooksMainInfo(): BooksDto
    suspend fun fetchDetailsInfo(): BooksDto
}