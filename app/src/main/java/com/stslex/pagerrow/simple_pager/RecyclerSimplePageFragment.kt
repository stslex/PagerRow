package com.stslex.pagerrow.simple_pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.stslex.pagerrow.ItemModel
import com.stslex.pagerrow.R
import com.stslex.pagerrow.databinding.FragmentSimplePageBinding
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class RecyclerSimplePageFragment : Fragment() {

    private val currentPage: Int
        get() = _currentPage

    private val _selectedItem = MutableStateFlow(ItemModel(""))
    private val selectedItem: StateFlow<ItemModel>
        get() = _selectedItem.asStateFlow()

    private var _binding: FragmentSimplePageBinding? = null
    private val binding: FragmentSimplePageBinding
        get() = requireNotNull(_binding)

    private val adapter by lazy {
        RecyclerSimpleViewAdapter(
            onItemClick = { item ->
                _selectedItem.value = item
            },
            selectedColor = if (currentPage == 0) {
                getColor(R.color.item_1_selected)
            } else {
                getColor(R.color.item_2_selected)
            },
            unSelectedColor = if (currentPage == 0) {
                getColor(R.color.item_1_unselected)
            } else {
                getColor(R.color.item_2_unselected)
            }
        )
    }

    private fun getColor(
        @ColorRes colorRes: Int
    ): Int = ContextCompat.getColor(
        requireContext(), colorRes
    )

    private val items by lazy {
        mutableListOf<ItemModel>().apply {
            repeat(10) { index ->
                add(ItemModel(index.toString()))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSimplePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter
        adapter.setItems(items)

        selectedItem
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach {
                adapter.updateItems(it)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        binding.nestedScrollSwitcher.setOnCheckedChangeListener { _, isChecked ->
            binding.root.isNestedScrollingEnabled = isChecked
            binding.recyclerView.isNestedScrollingEnabled = isChecked
        }
    }

    companion object {

        private var _currentPage: Int = -1

        val instance: (Int) -> Fragment
            get() = { pageNum ->
                _currentPage = pageNum
                RecyclerSimplePageFragment()
            }
    }
}