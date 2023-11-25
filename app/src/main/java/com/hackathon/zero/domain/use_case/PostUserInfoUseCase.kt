package com.hackathon.zero.domain.use_case

import com.hackathon.zero.data.UserInfo
import com.hackathon.zero.domain.UserInfoRepository
import com.hackathon.zero.util.Constants.ERROR_UNKNOWN
import com.hackathon.zero.core.Resource
import com.hackathon.zero.util.Constants.HTTP_STATUS_REDIRECTION
import com.hackathon.zero.util.Constants.HTTP_STATUS_SUCCESSFUL
import com.hackathon.zero.util.isSuccessful
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.util.concurrent.CancellationException
import javax.inject.Inject

class PostUserInfoUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository
) {
    operator fun invoke(userInfo: UserInfo) = flow {
        try {
            emit(Resource.loading())
            val response = userInfoRepository.postUserInfo(userInfo)
            if (isSuccessful(response.status)) {
                emit(Resource.success(response.data))
            } else {
                emit(Resource.error(response.message))
            }
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            emit(Resource.error(e.localizedMessage ?: ERROR_UNKNOWN))
        }
    }
}