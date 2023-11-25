package com.hackathon.zero.base

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackathon.zero.HomeViewModel
import com.hackathon.zero.core.Resource
import com.hackathon.zero.data.UserInfoInput
import com.hackathon.zero.domain.use_case.GetUserInfoUseCase
import com.hackathon.zero.domain.use_case.PostUserInfoUseCase
import com.hackathon.zero.util.Constants.USER_ID
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
    private val sp: SharedPreferencesUtil
): ViewModel(), HomeViewModel {

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

    private val _sharedAction: MutableSharedFlow<Intent> = MutableSharedFlow()
    override val sharedAction: SharedFlow<Intent>
        get() = _sharedAction

    override fun getUserInfo() {
        val userId = sp.getInt(USER_ID, USER_ID_NONE)
        if (userId != USER_ID_NONE) postUserInfoValue(userId) else return
    }

    override fun sharedClicked() {

    }

    private fun postUserInfoValue(userId: Int) {
        getUserInfoUseCase(userId).onEach { userInfoResource ->
            if (isLoading(userInfoResource)) {
                _isLoading.value = true
            } else if (isSuccess(userInfoResource)) {
                userInfoResource.data?.let { userInfo ->
                    _profileName.value = userInfo.name
                    _sugar.value = userInfo.sugar
                    _calories.value = userInfo.calorie
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