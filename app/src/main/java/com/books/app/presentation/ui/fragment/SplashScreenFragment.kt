package com.books.app.presentation.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.books.app.core.fragment.BaseBindingFragment
import com.books.app.databinding.FragmentSplashScreenBinding


class SplashScreenFragment : BaseBindingFragment<FragmentSplashScreenBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSplashScreenBinding
        get() = FragmentSplashScreenBinding::inflate

    override fun init() {

    }

}