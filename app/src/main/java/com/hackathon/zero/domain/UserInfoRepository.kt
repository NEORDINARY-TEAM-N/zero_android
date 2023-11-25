package com.hackathon.zero.domain

import com.hackathon.zero.core.dto.ResponseBody
import com.hackathon.zero.data.UserInfo

interface UserInfoRepository {
    suspend fun postUserInfo(userInfo: UserInfo): ResponseBody<String?>
}