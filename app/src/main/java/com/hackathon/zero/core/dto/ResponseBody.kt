package com.hackathon.zero.core.dto

data class ResponseBody<T: Any?>(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val data: T?
)
