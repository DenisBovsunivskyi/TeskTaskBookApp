package com.books.app.presentation.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.books.app.core.fragment.BaseBindingFragment
import com.books.app.databinding.FragmentMainScreenBinding
import com.books.app.presentation.ui.viewmodels.MainBooksViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreenFragment : BaseBindingFragment<FragmentMainScreenBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainScreenBinding
        get() = FragmentMainScreenBinding::inflate
    private val mainBooksViewModel by activityViewModels<MainBooksViewModel>()
    override fun init() {
        mainBooksViewModel.toString()
    }

}