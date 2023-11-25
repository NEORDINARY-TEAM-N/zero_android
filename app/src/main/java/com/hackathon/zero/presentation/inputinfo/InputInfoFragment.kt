package com.hackathon.zero.presentation.inputinfo

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.hackathon.zero.R
import com.hackathon.zero.base.BaseFragment
import com.hackathon.zero.databinding.FragmentInputInfoBinding
import kotlinx.coroutines.launch

class InputInfoFragment : BaseFragment<FragmentInputInfoBinding>(R.layout.fragment_input_info) {

    private val viewModel: InputInfoViewModel by viewModels<InputInfoViewModelImpl>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.moveTo.collect {
                runCatching {
                    findNavController().navigate(it)
                }
            }
        }
    }
}