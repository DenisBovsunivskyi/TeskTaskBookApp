package com.books.app.presentation.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.books.app.R
import com.books.app.core.fragment.BaseBindingFragment
import com.books.app.databinding.FragmentDetailsScreenBinding
import com.books.app.presentation.adapter.HorizontalBookDetailsAdapter
import com.books.app.presentation.adapter.HorizontalDetailsViewPagerAdapter
import com.books.app.presentation.ui.viewmodels.DetailsScreenViewModel
import com.books.app.presentation.util.MarginHorizontalItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class DetailsScreenFragment : BaseBindingFragment<FragmentDetailsScreenBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailsScreenBinding
        get() = FragmentDetailsScreenBinding::inflate


    private val detailsViewModel by activityViewModels<DetailsScreenViewModel>()
    private val viewPagerAdapter by lazy { HorizontalDetailsViewPagerAdapter() }
    private val horizontalRvAdapter by lazy { HorizontalBookDetailsAdapter() }
    private val args: DetailsScreenFragmentArgs by navArgs()

    override fun init() {
        detailsViewModel.setSelectedBook(args.selectedBook)
        detailsViewModel.setRecommendedBooksIndexes(args.recommendedBooks.toList())
    }

    override fun initListeners() {
        binding.popBackBtn.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
        binding.detailsViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                detailsViewModel.getDetailsBookListLiveData().value?.find { it.id == position }
                    ?.let { detailsViewModel.setSelectedBook(it) }

            }

        })
    }

    override fun initViewModels() {
        detailsViewModel.getDetailsBookListLiveData().observe(viewLifecycleOwner) { books ->
            viewPagerAdapter.differ.submitList(books)
            binding.detailsViewPager.setCurrentItem(args.selectedBook.id, false)

        }
        detailsViewModel.getSelectedBookLiveData().observe(viewLifecycleOwner) { book ->
            binding.model = book
        }
        detailsViewModel.getRecommendedBookListLiveData().observe(viewLifecycleOwner) { books ->
            horizontalRvAdapter.differ.submitList(books)
        }
    }

    override fun initViews() {
        //InitViewPager
        binding.detailsViewPager.adapter = viewPagerAdapter
        binding.detailsViewPager.offscreenPageLimit = 3
        binding.detailsViewPager.clipChildren = false
        binding.detailsViewPager.clipToPadding = false
        binding.detailsViewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val value: Float = 1 - abs(position)
            page.scaleY = 0.85F + value * 0.15F
        }
        binding.detailsViewPager.setPageTransformer(compositePageTransformer)
        //Init Recycler
        binding.detailsRv.apply {
            this.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            this.adapter = horizontalRvAdapter
            this.addItemDecoration(
                MarginHorizontalItemDecoration(
                    leftSize = this.context.resources.getDimensionPixelSize(
                        R.dimen.recycler_margin_start
                    ), rightSize = this.context.resources.getDimensionPixelSize(
                        R.dimen.recycler_margin_end
                    )
                )
            )
        }
    }


}