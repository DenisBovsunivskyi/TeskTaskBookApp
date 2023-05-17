package com.books.app.presentation.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.books.app.core.ResponseInfo
import com.books.app.domain.usecase.FetchBannerInfoUseCase
import com.books.app.domain.usecase.FetchMainBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainBooksViewModel @Inject constructor(
    private val fetchBannerInfoUseCase: FetchBannerInfoUseCase,
    private val fetchMainBooksUseCase: FetchMainBooksUseCase
) : ViewModel() {

    init {
        fetchBookBannersInfo()
        fetchMainBooks()
    }

    private fun fetchBookBannersInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchBannerInfoUseCase.execute().collect { response ->
                when (response) {
                    is ResponseInfo.Success -> {
                        println(response.data)
                    }

                    is ResponseInfo.Error -> {
                        println(response.rawResponse)
                    }

                }
            }
        }

    }

    fun fetchMainBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchMainBooksUseCase.execute().collect { response ->
                when (response) {
                    is ResponseInfo.Success -> {
                        println(response.data)
                    }

                    is ResponseInfo.Error -> {
                        println(response.rawResponse)
                    }

                }
            }
        }
    }

}