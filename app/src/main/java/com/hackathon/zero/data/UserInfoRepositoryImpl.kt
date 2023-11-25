package com.hackathon.zero.data

import com.hackathon.zero.core.dto.ResponseBody
import com.hackathon.zero.di.api.ZeroUserApi
import com.hackathon.zero.domain.UserInfoRepository

class UserInfoRepositoryImpl(
    private val api: ZeroUserApi
) : UserInfoRepository {
    override suspend fun postUserInfo(userInfoInput: UserInfoInput): ResponseBody<PostUserInfo?> {
        return api.postUserInfo(userInfoInput)
    }

    override suspend fun getUserInfo(userId: Int): ResponseBody<HomeUserInfo?> {
        return api.getUserInfo(userId)
    }
}