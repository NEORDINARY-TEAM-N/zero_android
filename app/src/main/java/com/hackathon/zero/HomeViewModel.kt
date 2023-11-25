package com.hackathon.zero

import android.content.Intent
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface HomeViewModel {
    val profileName: StateFlow<String>
    val sugar: StateFlow<Double>
    val calories: StateFlow<Int>
    val sharedAction: SharedFlow<Intent>

    fun sharedClicked()
}