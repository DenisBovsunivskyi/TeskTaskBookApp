package com.books.app.presentation.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.books.app.core.fragment.BaseBindingFragment
import com.books.app.databinding.FragmentMainScreenBinding

class MainScreenFragment : BaseBindingFragment<FragmentMainScreenBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainScreenBinding
        get() = FragmentMainScreenBinding::inflate

    override fun init() {


    }

}