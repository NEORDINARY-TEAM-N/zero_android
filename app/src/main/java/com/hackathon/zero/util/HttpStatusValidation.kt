package com.hackathon.zero.util

fun isSuccessful(status: Int): Boolean {
    return status == Constants.HTTP_STATUS_SUCCESSFUL
}