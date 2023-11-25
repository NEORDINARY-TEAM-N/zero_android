package com.hackathon.zero

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.hackathon.zero.base.BaseFragment
import com.hackathon.zero.base.HomeViewModelImpl
import androidx.fragment.app.viewModels
import com.hackathon.zero.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.sharedAction.collect {

            }
        }
    }
}