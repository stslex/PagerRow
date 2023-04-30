package com.stslex.pagerrow.pages.recycler

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.stslex.pagerrow.ItemModel

private val items by lazy {
    mutableListOf<ItemModel>().apply {
        repeat(10) { index ->
            add(ItemModel(index.toString()))
        }
    }
}

@Composable
fun FirstRecyclerPage(
    modifier: Modifier = Modifier
) {
    SingleRecyclerPage(
        modifier = modifier,
        items = items,
        selectedColor = Color.Cyan,
        unselectedColor = Color.Magenta
    )
}
