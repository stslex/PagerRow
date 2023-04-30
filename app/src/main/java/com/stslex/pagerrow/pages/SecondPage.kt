package com.stslex.pagerrow.pages

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

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
        selectedColor = Color.Green,
        unselectedColor = Color.Blue
    )
}