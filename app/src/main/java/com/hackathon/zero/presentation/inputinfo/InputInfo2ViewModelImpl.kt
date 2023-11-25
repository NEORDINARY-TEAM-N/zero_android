package com.hackathon.zero.presentation.inputinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InputInfo2ViewModelImpl: ViewModel(), InputInfo2ViewModel {

    override val seekbarValue: MutableStateFlow<Int>  = MutableStateFlow(0)
    private val _managePurpose: MutableStateFlow<String> = MutableStateFlow("")
    override val managePurpose: StateFlow<String> get() = _managePurpose

    private val _showDialog: MutableSharedFlow<Boolean> = MutableSharedFlow()
    override val showDialog: SharedFlow<Boolean> get() = _showDialog
    private var selectManagementItemIndex = -1

    override fun doneClicked() {

    }

    override fun manageClick() {
        viewModelScope.launch { _showDialog.emit(true) }
    }

    override fun selectedManagementItem(index: Int) {
        selectManagementItemIndex = index
    }
}