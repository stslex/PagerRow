package com.stslex.pagerrow.pages

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.stslex.pagerrow.ItemModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SingleItem(
    modifier: Modifier = Modifier,
    onItemClick: (ItemModel) -> Unit,
    item: ItemModel,
    isSelected: Boolean,
    selectedColor: Color,
    unselectedColor: Color
) {
    Card(
        modifier = modifier
            .padding(10.dp)
            .size(100.dp),
        backgroundColor = if (isSelected) {
            selectedColor
        } else {
            unselectedColor
        },
        onClick = {
            onItemClick(item)
        }
    ) {
        Text(
            text = item.uuid,
            style = MaterialTheme.typography.h2,
            textAlign = TextAlign.Center
        )
    }
}