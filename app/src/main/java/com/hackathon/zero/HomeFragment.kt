package com.hackathon.zero

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.hackathon.zero.base.BaseFragment
import com.hackathon.zero.base.HomeViewModelImpl
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController

import androidx.navigation.fragment.findNavController
import com.hackathon.zero.databinding.FragmentHomeBinding
import com.hackathon.zero.presentation.search.SearchRVAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()
    private lateinit var rvAdapter: SearchRVAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.llTotalIntake.setOnClickListener {
            findNavController().navigate(
                R.id.action_homeFragment_to_myFragment
            )
        }
        binding.vm = viewModel
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.sharedAction.collect {

            }
        }

        binding.btnStart.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_privateInfoFragment)
        }

        initializeRVAdapter()
    }

    private fun initializeRVAdapter() {
        rvAdapter = SearchRVAdapter()
        rvAdapter.setList(viewModel.productList.value)
    }
}