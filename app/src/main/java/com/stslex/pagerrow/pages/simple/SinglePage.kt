package com.stslex.pagerrow.pages.simple

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SinglePage(
    modifier: Modifier = Modifier,
    items: List<String>,
    selectedColor: Color,
    unselectedColor: Color
) {
    var selectedItem by remember {
        mutableStateOf<String?>(null)
    }

    Box(modifier = modifier.fillMaxSize()) {
        LazyRow(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            items(
                items = items,
                key = { it }
            ) { item ->
                Card(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(100.dp),
                    backgroundColor = if (selectedItem == item) {
                        selectedColor
                    } else {
                        unselectedColor
                    },
                    onClick = {
                        selectedItem = item
                    }
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = item,
                        style = MaterialTheme.typography.h2,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
