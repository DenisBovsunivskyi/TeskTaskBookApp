package com.books.app.presentation.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.books.app.R
import com.books.app.core.fragment.BaseBindingFragment
import com.books.app.databinding.FragmentMainScreenBinding
import com.books.app.presentation.adapter.MainBooksAdapter
import com.books.app.presentation.ui.viewmodels.MainBooksViewModel
import com.books.app.presentation.util.MarginVerticalItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreenFragment : BaseBindingFragment<FragmentMainScreenBinding>() {
    private val mainRecyclerAdapter by lazy { MainBooksAdapter() }
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainScreenBinding
        get() = FragmentMainScreenBinding::inflate
    private val mainBooksViewModel by activityViewModels<MainBooksViewModel>()


    override fun init() {

    }

    override fun initListeners() {
        super.initListeners()
        mainRecyclerAdapter.setBockClickListener {
            println(it)
        }
    }
    override fun initViews() {
        binding.bookScreenRv.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL, false
            )
            addItemDecoration(
                MarginVerticalItemDecoration(
                    bottomSize = this.context.resources.getDimensionPixelSize(
                        R.dimen.recycler_margin
                    ),
                    topSize = this.context.resources.getDimensionPixelSize(
                        R.dimen.recycler_margin
                    )

                )
            )
        }
        binding.bookScreenRv.adapter = mainRecyclerAdapter

    }

    override fun initViewModels() {
        mainBooksViewModel.getMainBooksLiveData().observe(viewLifecycleOwner) {
            mainRecyclerAdapter.submitList(it)
        }
    }


}