package com.hackathon.zero.data


import com.google.gson.annotations.SerializedName

data class UserInfoInput(
    @SerializedName("user_id")
    val userId: Long,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("weight")
    val weight: Int
)