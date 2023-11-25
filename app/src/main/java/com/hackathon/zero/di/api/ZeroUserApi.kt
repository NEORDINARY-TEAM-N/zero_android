package com.hackathon.zero.di.api

import com.hackathon.zero.core.dto.ResponseBody
import com.hackathon.zero.data.UserInfo
import retrofit2.http.Body
import retrofit2.http.POST

interface ZeroUserApi {
    @POST("api/v1/user")
    suspend fun postUserInfo(
        @Body userInfo: UserInfo
    ): ResponseBody<String?>
}