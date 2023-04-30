package com.stslex.pagerrow.recycler_pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.stslex.pagerrow.databinding.FragmentPagerBinding
import com.stslex.pagerrow.native_pager.ViewPagerAdapter

class PagerRecyclerFragment : Fragment() {

    private var _binding: FragmentPagerBinding? = null
    private val binding: FragmentPagerBinding
        get() = requireNotNull(_binding)

    private val pagerAdapter by lazy {
        RecyclerViewPagerAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = pagerAdapter
        TabLayoutMediator(binding.pagerTabLayout, binding.viewPager) { tab, position ->
            tab.text = "Page ${position.inc()}"
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}