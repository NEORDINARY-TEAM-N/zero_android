package com.hackathon.zero.presentation.inputinfo

import android.os.Bundle
import android.util.Log
import android.view.View
import com.hackathon.zero.R
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.hackathon.zero.base.BaseFragment
import com.hackathon.zero.databinding.FragmentInputInfo2Binding
import com.hackathon.zero.presentation.dialog.ManagementPurposeDialog
import kotlinx.coroutines.launch


class InputInfo2Fragment : BaseFragment<FragmentInputInfo2Binding>(R.layout.fragment_input_info2) {

    private val viewModel: InputInfo2ViewModel by viewModels<InputInfo2ViewModelImpl>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        Log.e("gender", arguments?.get("gender").toString())
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.showDialog.collect {
                if(it) ManagementPurposeDialog(requireContext()) { index ->
                    viewModel.selectedManagementItem(index)
                }.show()
            }
        }
    }
}