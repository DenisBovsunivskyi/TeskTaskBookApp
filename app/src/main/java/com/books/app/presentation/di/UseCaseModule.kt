package com.books.app.presentation.di

import com.books.app.domain.BooksRepository
import com.books.app.domain.usecase.FetchBannerInfoUseCase
import com.books.app.domain.usecase.FetchDetailsBooksUseCase
import com.books.app.domain.usecase.FetchMainBooksUseCase
import com.books.app.domain.usecase.FetchRecommendedBookUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideFetchBannerInfoUseCase(bookRepository: BooksRepository): FetchBannerInfoUseCase {
        return FetchBannerInfoUseCase(bookRepository)
    }
    @Provides
    @Singleton
    fun provideMainBooksUseCase(bookRepository: BooksRepository): FetchMainBooksUseCase {
        return FetchMainBooksUseCase(bookRepository)
    }
    @Provides
    @Singleton
    fun provideRecommendedBooksUseCase(bookRepository: BooksRepository): FetchRecommendedBookUseCase {
        return FetchRecommendedBookUseCase(bookRepository)
    }
    @Provides
    @Singleton
    fun provideDetailsBooksUseCase(bookRepository: BooksRepository): FetchDetailsBooksUseCase {
        return FetchDetailsBooksUseCase(bookRepository)
    }
}