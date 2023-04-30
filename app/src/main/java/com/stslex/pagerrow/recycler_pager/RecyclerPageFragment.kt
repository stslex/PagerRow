package com.stslex.pagerrow.recycler_pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.stslex.pagerrow.pages.recycler.FirstRecyclerPage
import com.stslex.pagerrow.pages.recycler.SecondRecyclerPage

class RecyclerPageFragment : Fragment() {

    private val currentPage: Int
        get() = _currentPage

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(strategy = ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            when (currentPage) {
                0 -> FirstRecyclerPage()
                1 -> SecondRecyclerPage()
            }
        }
    }

    companion object {

        private var _currentPage: Int = -1

        val instance: (Int) -> Fragment
            get() = { pageNum ->
                _currentPage = pageNum
                RecyclerPageFragment()
            }
    }
}