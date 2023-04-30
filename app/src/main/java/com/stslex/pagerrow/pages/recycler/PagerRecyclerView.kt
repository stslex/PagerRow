package com.stslex.pagerrow.pages.recycler

import android.content.Context
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.compose.ui.graphics.Color
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stslex.pagerrow.ItemModel
import com.stslex.pagerrow.recycler_pager.RecyclerViewAdapter

class PagerRecyclerView(
    context: Context,
    onItemClick: (ItemModel) -> Unit,
    isSelected: (ItemModel) -> Boolean,
    selectedColor: Color,
    unselectedColor: Color
) : RecyclerView(context) {

    private val recyclerAdapter by lazy {
        RecyclerViewAdapter(onItemClick, isSelected, selectedColor, unselectedColor)
    }

    init {
        layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
        adapter = recyclerAdapter
        isNestedScrollingEnabled = false
        layoutParams = LayoutParams(
            MATCH_PARENT,
            WRAP_CONTENT
        )
    }

    fun setItems(items: List<ItemModel>) {
        recyclerAdapter.setItems(items)
    }
}