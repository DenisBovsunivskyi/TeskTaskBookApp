package com.books.app.presentation.ui.activity

import android.os.Bundle
import com.books.app.core.activity.BaseActivity
import com.books.app.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity:BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun init(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}