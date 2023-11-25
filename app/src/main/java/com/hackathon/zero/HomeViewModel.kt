package com.hackathon.zero

import android.content.Intent
import com.hackathon.zero.data.Product
import com.hackathon.zero.data.ProductSearchItem
import com.hackathon.zero.data.UserInfoInput
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface HomeViewModel {
    val isLoading: StateFlow<Boolean>
    val errorMessage: StateFlow<String?>
    val profileName: StateFlow<String>
    val sugar: StateFlow<Double>
    val calories: StateFlow<Int>
    val stamps: StateFlow<Map<String, Boolean>>
    val sharedAction: SharedFlow<Intent>
    var query: MutableStateFlow<String>
    val productList: StateFlow<MutableList<ProductSearchItem?>>

    fun sharedClicked()

    fun getUserInfo()

    fun queryChanged(query: String, lastId: Int = 0)
}