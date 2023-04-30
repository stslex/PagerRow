package com.stslex.pagerrow.recycler_pager

import android.view.ViewGroup
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.recyclerview.widget.RecyclerView
import com.stslex.pagerrow.ItemModel
import com.stslex.pagerrow.pages.SingleItem
import com.stslex.pagerrow.recycler_pager.RecyclerViewAdapter.ViewHolder

class RecyclerViewAdapter(
    private val onItemClick: (ItemModel) -> Unit,
    private val isSelected: (ItemModel) -> Boolean,
    private val selectedColor: Color,
    private val unselectedColor: Color
) : RecyclerView.Adapter<ViewHolder>() {

    private val _items: MutableList<ItemModel> = mutableListOf()
    private val items: List<ItemModel>
        get() = _items.toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            view = ComposeView(parent.context).apply {
                this.setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            },
            onItemClick = onItemClick,
            isSelected = isSelected,
            selectedColor = selectedColor,
            unselectedColor = unselectedColor
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.getOrNull(position) ?: return
        holder.bind(item)
    }

    fun setItems(itemList: List<ItemModel>) {
        _items.clear()
        _items.addAll(itemList)
        notifyDataSetChanged()
    }

    class ViewHolder(
        view: ComposeView,
        private val onItemClick: (ItemModel) -> Unit,
        private val isSelected: (ItemModel) -> Boolean,
        private val selectedColor: Color,
        private val unselectedColor: Color
    ) : RecyclerView.ViewHolder(view) {

        private var _currentItem = mutableStateOf(ItemModel(""))
        private val currentItem: ItemModel
            get() = _currentItem.value

        init {
            view.setContent {
                SingleItem(
                    onItemClick = onItemClick,
                    item = currentItem,
                    isSelected = isSelected(currentItem),
                    selectedColor = selectedColor,
                    unselectedColor = unselectedColor
                )
            }
        }

        fun bind(
            item: ItemModel
        ) {
            _currentItem.value = item
        }
    }
}