package com.hackathon.zero

import android.content.Intent
import com.hackathon.zero.data.UserInfoInput
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface HomeViewModel {
    val isLoading: StateFlow<Boolean>
    val errorMessage: StateFlow<String?>
    val profileName: StateFlow<String>
    val sugar: StateFlow<Double>
    val calories: StateFlow<Int>
    val sharedAction: SharedFlow<Intent>

    fun sharedClicked()

    fun getUserInfo()
}