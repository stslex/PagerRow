package com.stslex.pagerrow.pages.simple

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.stslex.pagerrow.R

private val items by lazy {
    mutableListOf<String>().apply {
        repeat(10) { index ->
            add(index.toString())
        }
    }
}

@Composable
fun SecondPage(
    modifier: Modifier = Modifier
) {
    SinglePage(
        modifier = modifier,
        items = items,
        selectedColor = colorResource(id = R.color.item_2_selected),
        unselectedColor = colorResource(id = R.color.item_2_unselected)
    )
}