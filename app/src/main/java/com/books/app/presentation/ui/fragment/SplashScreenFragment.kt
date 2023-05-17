package com.books.app.presentation.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.books.app.core.fragment.BaseBindingFragment
import com.books.app.databinding.FragmentSplashScreenBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashScreenFragment : BaseBindingFragment<FragmentSplashScreenBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSplashScreenBinding
        get() = FragmentSplashScreenBinding::inflate

    override fun init() {
        viewLifecycleOwner.lifecycleScope.launch {
            delay(2000)
            openMainFragment()
        }

    }
    private fun openMainFragment() {
        findNavController().navigate(SplashScreenFragmentDirections.actionGlobalMainFragment())
    }
}