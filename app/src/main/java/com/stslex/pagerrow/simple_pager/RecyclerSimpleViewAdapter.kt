package com.stslex.pagerrow.simple_pager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import com.stslex.pagerrow.ItemModel
import com.stslex.pagerrow.databinding.ItemSimpleRecyclerBinding
import com.stslex.pagerrow.simple_pager.RecyclerSimpleViewAdapter.ViewHolder

class RecyclerSimpleViewAdapter(
    private val onItemClick: (ItemModel) -> Unit,
    @ColorInt private val selectedColor: Int,
    @ColorInt private val unSelectedColor: Int,
) : RecyclerView.Adapter<ViewHolder>() {

    private val _items: MutableList<ItemModel> = mutableListOf()
    private val items: List<ItemModel>
        get() = _items.toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            binding = ItemSimpleRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClick = onItemClick,
            selectedColor = selectedColor,
            unSelectedColor = unSelectedColor
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

    fun updateItems(item: ItemModel) {
        val currentSelectedIndex = items.indexOfFirst { it.uuid == item.uuid }
        if (currentSelectedIndex == -1) return

        val previousSelectedIndex = items.indexOfFirst { it.isSelected }
        items.getOrNull(previousSelectedIndex)?.let {
            _items[previousSelectedIndex] = it.copy(isSelected = false)
            notifyItemChanged(previousSelectedIndex)
        }

        _items[currentSelectedIndex] = item
        notifyItemChanged(currentSelectedIndex)
    }

    class ViewHolder(
        private val binding: ItemSimpleRecyclerBinding,
        private val onItemClick: (ItemModel) -> Unit,
        @ColorInt private val selectedColor: Int,
        @ColorInt private val unSelectedColor: Int,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: ItemModel
        ) {
            binding.root.setOnClickListener {
                onItemClick(item.copy(isSelected = true))
            }
            binding.textView.text = item.uuid

            val color = if (item.isSelected) {
                selectedColor
            } else {
                unSelectedColor
            }
            binding.root.setCardBackgroundColor(color)
        }
    }
}