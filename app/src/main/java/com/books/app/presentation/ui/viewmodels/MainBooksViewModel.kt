package com.books.app.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.books.app.core.ResponseInfo
import com.books.app.domain.usecase.FetchBannerInfoUseCase
import com.books.app.domain.usecase.FetchMainBooksUseCase
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
    private val fetchMainBooksUseCase: FetchMainBooksUseCase
) : ViewModel() {
    private val mainBooksData: MutableLiveData<List<MainBookRecyclerData>> =
        MutableLiveData()

    fun getMainBooksLiveData(): LiveData<List<MainBookRecyclerData>> = mainBooksData

    init {
        fetchCombinedMainInfo()
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
                            println(banners.rawResponse)
                        }
                    }
                    when (books) {
                        is ResponseInfo.Success -> {
                            val listOfGenres = books.data.flatMap { listOf(it.genre) }.distinct()
                            listOfGenres.forEachIndexed { index, genre ->
                                val subList = books.data.filter { it.genre == genre }
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

                        is ResponseInfo.Error -> {}
                    }
                    tempList
                }.collect {
                mainBooksData.postValue(it)
            }
        }

    }


}