package com.stslex.pagerrow.compose_pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.style.TextAlign
import androidx.fragment.app.Fragment
import com.stslex.pagerrow.pages.simple.FirstPage
import com.stslex.pagerrow.pages.PagerScreen
import com.stslex.pagerrow.pages.simple.SecondPage
import kotlinx.coroutines.launch

class PagerComposeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            MainScreen()
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun MainScreen(
        modifier: Modifier = Modifier
    ) {
        val pagerState = rememberPagerState(
            initialPage = PagerScreen.FIRST.index
        )
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            TabPagerRow(pagerState = pagerState)
            HorizontalPager(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                pageCount = 2,
                state = pagerState
            ) { pageNum ->
                when (pageNum) {
                    PagerScreen.FIRST.index -> FirstPage()
                    PagerScreen.SECOND.index -> SecondPage()
                }
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun TabPagerRow(
        pagerState: PagerState,
        modifier: Modifier = Modifier
    ) {
        val coroutineScope = rememberCoroutineScope()

        TabRow(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            selectedTabIndex = pagerState.currentPage
        ) {
            PagerScreen.values().forEach { pagerScreen ->
                Tab(
                    selected = pagerState.currentPage == pagerScreen.index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerScreen.index)
                        }
                    },
                    text = {
                        Text(
                            text = "Page ${pagerScreen.index.inc()}",
                            textAlign = TextAlign.Center
                        )
                    },
                )
            }
        }
    }
}