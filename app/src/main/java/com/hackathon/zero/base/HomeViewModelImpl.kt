package com.hackathon.zero.base

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackathon.zero.HomeViewModel
import com.hackathon.zero.data.ProductSearchItem
import com.hackathon.zero.domain.use_case.GetHomeDataUseCase
import com.hackathon.zero.domain.use_case.GetProductListUseCase
import com.hackathon.zero.domain.use_case.GetUserInfoUseCase
import com.hackathon.zero.util.Constants.FRIDAY
import com.hackathon.zero.util.Constants.MONDAY
import com.hackathon.zero.util.Constants.SATURDAY
import com.hackathon.zero.util.Constants.SUNDAY
import com.hackathon.zero.util.Constants.THURSDAY
import com.hackathon.zero.util.Constants.TUESDAY
import com.hackathon.zero.util.Constants.USER_ID
import com.hackathon.zero.util.Constants.WEDNESDAY
import com.hackathon.zero.util.SharedPreferencesUtil
import com.hackathon.zero.util.isLoading
import com.hackathon.zero.util.isSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getProductListUseCase: GetProductListUseCase,
    private val getHomeDataUseCase: GetHomeDataUseCase,
    private val sp: SharedPreferencesUtil
): ViewModel(), HomeViewModel {

    private var stampMap = listOf(
        false,
        false,
        false,
        false,
        false,
        false,
        false
    )

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val isLoading: StateFlow<Boolean>
        get() = _isLoading

    private val _errorMessage: MutableStateFlow<String?> = MutableStateFlow(null)
    override val errorMessage: StateFlow<String?>
        get() = _errorMessage

    private val _profileName : MutableStateFlow<String> = MutableStateFlow(UNREGISTERED_USER)
    override val profileName: StateFlow<String>
        get() = _profileName

    private val _sugar: MutableStateFlow<Double> = MutableStateFlow(0.0)
    override val sugar: StateFlow<Double>
        get() = _sugar

    private val _calories: MutableStateFlow<Int> = MutableStateFlow(0)
    override val calories: StateFlow<Int>
        get() = _calories

    private val _stamps = MutableStateFlow(stampMap)
    override val stamps: StateFlow<List<Boolean>>
        get() = _stamps

    override var query = MutableStateFlow<String>("")

    private val _sharedAction: MutableSharedFlow<Intent> = MutableSharedFlow()
    override val sharedAction: SharedFlow<Intent>
        get() = _sharedAction

    private val _productList: MutableStateFlow<MutableList<ProductSearchItem?>> = MutableStateFlow(
        mutableListOf()
    )
    override val productList: StateFlow<MutableList<ProductSearchItem?>>
        get() = _productList

    override fun getUserInfo() {
        val userId = sp.getInt(USER_ID, USER_ID_NONE)
        if (userId != USER_ID_NONE) postUserInfoValue(userId) else return
    }

    override fun sharedClicked() {

    }

    override fun queryChanged(query: String, lastId: Int) {
        getProductListUseCase(query, lastId).onEach { productList ->
            if (isLoading(productList)) {
                _isLoading.value = true
            } else if (isSuccess(productList)) {
                _productList.value = productList.data ?: mutableListOf()
            } else {
                _errorMessage.value = productList.message
            }
        }.launchIn(viewModelScope)
    }

    private fun postUserInfoValue(userId: Int) {
        getHomeDataUseCase(userId).onEach { userInfoResource ->
            if (isLoading(userInfoResource)) {
                _isLoading.value = true
            } else if (isSuccess(userInfoResource)) {
                userInfoResource.data?.let { userInfo ->
                    _profileName.value = userInfo.username
                    _sugar.value = userInfo.totalSugar.toDouble()
                    _calories.value = userInfo.totalKcal.toInt()
                    var i = -1
                    _stamps.value.map {
                        i++
                        userInfo.completedDate[i] == 1
                    }
                }
            } else {
                _errorMessage.value = userInfoResource.message
            }
        }.launchIn(viewModelScope)
    }




    companion object {
        const val UNREGISTERED_USER = "User"
        const val USER_ID_NONE = 0
    }
}