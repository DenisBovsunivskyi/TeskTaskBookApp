package com.books.app.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.books.app.core.Event
import com.books.app.core.ResponseInfo
import com.books.app.core.utils.text.UniversalText
import com.books.app.data.models.books.Books
import com.books.app.domain.usecase.FetchBannerInfoUseCase
import com.books.app.domain.usecase.FetchMainBooksUseCase
import com.books.app.domain.usecase.FetchRecommendedBookUseCase
import com.books.app.presentation.model.DataItemType
import com.books.app.presentation.model.MainBookRecyclerData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainBooksViewModel @Inject constructor(
    private val fetchBannerInfoUseCase: FetchBannerInfoUseCase,
    private val fetchMainBooksUseCase: FetchMainBooksUseCase,
    private val fetchRecommendedBookUseCase: FetchRecommendedBookUseCase
) : ViewModel() {
    private val mainBooksData: MutableLiveData<List<MainBookRecyclerData>> =
        MutableLiveData()
    var allBooksList: List<Books> = emptyList()
    private var recommendedBooks: List<Int> = emptyList()
    fun getRecommendedBooksList(): List<Int> = recommendedBooks
    fun getMainBooksLiveData(): LiveData<List<MainBookRecyclerData>> = mainBooksData

    init {
        fetchCombinedMainInfo()
        fetchRecommendedBooks()
    }
    private val mEventLiveData: MutableLiveData<Event<UniversalText>> =
        MutableLiveData<Event<UniversalText>>()
    fun getEventLiveData(): LiveData<Event<UniversalText>> {
        return mEventLiveData
    }
    private fun fetchRecommendedBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchRecommendedBookUseCase.execute().collect { response ->
                when (response) {
                    is ResponseInfo.Success -> {
                        recommendedBooks = response.data
                    }
                    else ->{

                    }
                }
            }
        }

    }

    private fun fetchCombinedMainInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchBannerInfoUseCase.execute()
                .combine(fetchMainBooksUseCase.execute()) { banners, books ->
                    val tempList = mainBooksData.value?.toMutableList() ?: mutableListOf()
                    when (banners) {
                        is ResponseInfo.Success -> {
                            tempList.add(
                                MainBookRecyclerData(
                                    0,
                                    DataItemType.BANNERS,
                                    null,
                                    bannerList = banners.data
                                )
                            )
                        }

                        is ResponseInfo.Error -> {
                            mEventLiveData.postValue(Event(banners.rawResponse))
                        }
                    }
                    when (books) {
                        is ResponseInfo.Success -> {
                            allBooksList = books.data
                            val listOfGenres = books.data.flatMap { listOf(it.genre) }.distinct()
                            listOfGenres.forEachIndexed { index, genre ->
                                val subList =
                                    books.data.filter { it.genre == genre }.toMutableList()
                                tempList.add(
                                    MainBookRecyclerData(
                                        index.plus(1),
                                        DataItemType.HORIZONTAL_LIST,
                                        genre,
                                        bannerList = null,
                                        bookList = subList
                                    )
                                )
                            }
                        }

                        is ResponseInfo.Error -> {
                            mEventLiveData.postValue(Event(books.rawResponse))
                        }
                    }
                    tempList
                }.collect {
                    mainBooksData.postValue(it)
                }
        }

    }

}