package com.books.app.data.data_source

import com.books.app.data.models.temp.BooksDto
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.gson.Gson
import kotlinx.coroutines.tasks.await


class BookDataSourceImpl(private val remoteConfig: FirebaseRemoteConfig) : BookDataSource {
    override suspend fun fetchBooksMainInfo(): BooksDto {
        return try {
            remoteConfig.fetchAndActivate().await()
            val booksJson = remoteConfig.getString("json_data")
            val booksDto = Gson().fromJson<BooksDto>(booksJson, BooksDto::class.java)
            booksDto
        } catch (e: Exception) {
            return BooksDto(emptyList(), emptyList(), emptyList())
        }
    }

    override suspend fun fetchDetailsInfo(): BooksDto {
        return try {
            remoteConfig.fetchAndActivate().await()
            val booksJson = remoteConfig.getString("details_carousel")
            val booksDto = Gson().fromJson<BooksDto>(booksJson, BooksDto::class.java)
            booksDto
        } catch (e: Exception) {
            return BooksDto(emptyList(), emptyList(), emptyList())
        }
    }
}