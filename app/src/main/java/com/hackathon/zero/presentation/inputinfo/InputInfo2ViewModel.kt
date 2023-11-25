package com.hackathon.zero.presentation.inputinfo

import android.content.Intent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface InputInfo2ViewModel {
    val seekbarValue: MutableStateFlow<Int>
    val managePurpose: StateFlow<String>
    val showDialog: SharedFlow<Boolean>

    fun doneClicked()
    fun manageClick()
    fun selectedManagementItem(index: Int)
}