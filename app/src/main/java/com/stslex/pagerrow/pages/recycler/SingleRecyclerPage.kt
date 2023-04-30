package com.stslex.pagerrow.pages.recycler

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import com.stslex.pagerrow.ItemModel

@Composable
fun SingleRecyclerPage(
    modifier: Modifier = Modifier,
    items: List<ItemModel>,
    selectedColor: Color,
    unselectedColor: Color
) {
    var selectedItem by remember {
        mutableStateOf<ItemModel?>(null)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(Alignment.CenterVertically)
                .align(Alignment.Center)
        ) {
            AndroidView(
                factory = { context ->
                    PagerRecyclerView(
                        context = context,
                        isSelected = { item ->
                            selectedItem == item
                        },
                        onItemClick = { item ->
                            selectedItem = item
                        },
                        selectedColor = selectedColor,
                        unselectedColor = unselectedColor
                    )
                }
            ) {
                it.setItems(items)
            }
        }

    }
}

