package com.books.app.presentation.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.books.app.domain.usecase.FetchBannerInfoUseCase
import com.books.app.domain.usecase.FetchMainBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainBooksViewModel @Inject constructor(
    private val fetchBannerInfoUseCase: FetchBannerInfoUseCase,
    private val fetchMainBooksUseCase: FetchMainBooksUseCase
    ) :
    ViewModel() {

}