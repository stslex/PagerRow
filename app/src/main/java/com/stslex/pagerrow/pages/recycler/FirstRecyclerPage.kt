package com.stslex.pagerrow.pages.recycler

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.stslex.pagerrow.ItemModel
import com.stslex.pagerrow.R

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
        selectedColor = colorResource(id = R.color.item_1_selected),
        unselectedColor = colorResource(id = R.color.item_1_unselected)
    )
}
