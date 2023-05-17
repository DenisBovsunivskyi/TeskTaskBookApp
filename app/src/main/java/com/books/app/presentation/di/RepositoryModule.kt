package com.books.app.presentation.di

import com.books.app.data.data_source.BookDataSource
import com.books.app.data.repository.BooksRepositoryImpl
import com.books.app.domain.BooksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideBooksRepository(
        booksDataSource: BookDataSource
    ): BooksRepository {
        return BooksRepositoryImpl(booksDataSource)
    }

}