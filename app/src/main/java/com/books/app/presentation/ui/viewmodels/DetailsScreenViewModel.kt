package com.books.app.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.books.app.core.ResponseInfo
import com.books.app.data.models.books.Books
import com.books.app.domain.usecase.FetchDetailsBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val detailsBooksUseCase: FetchDetailsBooksUseCase
) : ViewModel() {
    private var detailsBooksList: MutableLiveData<List<Books>> = MutableLiveData()
    fun getDetailsBookListLiveData(): LiveData<List<Books>> = detailsBooksList

    private var recommendedBooksIndexes: List<Int> = emptyList()
    fun setRecommendedBooksIndexes(list: List<Int>) {
        recommendedBooksIndexes = list
    }
    private var recommendedBooksList: MutableLiveData<List<Books>> = MutableLiveData()
    fun getRecommendedBookListLiveData(): LiveData<List<Books>> = recommendedBooksList

    private var selectedBook: MutableLiveData<Books> = MutableLiveData()
    fun getSelectedBookLiveData(): LiveData<Books> = selectedBook

    fun setSelectedBook(books: Books) {
        selectedBook.postValue(books)
    }

    init {
        fetchDetailsInfo()
    }

    private fun fetchDetailsInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            detailsBooksUseCase.execute().collect { response ->
                when (response) {
                    is ResponseInfo.Success -> {
                        detailsBooksList.postValue(response.data.sortedBy { it.id })
                        recommendedBooksList.postValue(response.data.filter {
                            recommendedBooksIndexes.contains(
                                it.id
                            )
                        })
                    }

                    is ResponseInfo.Error -> {

                    }
                }
            }
        }

    }

}