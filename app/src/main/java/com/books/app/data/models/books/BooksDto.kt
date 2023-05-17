package com.books.app.data.models.books


import com.google.gson.annotations.SerializedName

data class BooksDto(
    @SerializedName("books")
    val books: List<Book>
)