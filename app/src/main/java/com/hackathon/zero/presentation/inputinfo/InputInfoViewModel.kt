package com.hackathon.zero.presentation.inputinfo

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface InputInfoViewModel {

    val name: MutableStateFlow<String>
    val isGenderSelectMan: StateFlow<Boolean>
    val weight: StateFlow<Int>
    val height: StateFlow<Int>
    val age: StateFlow<Int>

    fun manClicked()
    fun womanClicked()
    fun weightClicked()
    fun heightClicked()
}