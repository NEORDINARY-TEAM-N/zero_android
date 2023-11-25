package com.hackathon.zero.base

import android.content.Intent
import androidx.lifecycle.ViewModel
import com.hackathon.zero.HomeViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModelImpl: ViewModel(), HomeViewModel {

    private val _profileName : MutableStateFlow<String> = MutableStateFlow("")
    override val profileName: StateFlow<String>
        get() = _profileName

    private val _sugar: MutableStateFlow<Double> = MutableStateFlow(0.0)
    override val sugar: StateFlow<Double>
        get() = _sugar

    private val _calories: MutableStateFlow<Int> = MutableStateFlow(0)
    override val calories: StateFlow<Int>
        get() = _calories

    private val _sharedAction: MutableSharedFlow<Intent> = MutableSharedFlow()
    override val sharedAction: SharedFlow<Intent>
        get() = _sharedAction

    override fun sharedClicked() {

    }

}