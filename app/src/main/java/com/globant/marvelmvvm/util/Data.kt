package com.globant.marvelmvvm.util

data class Data<RequestData>(var status: Status, var data: RequestData? = null, var error: Exception? = null)

enum class Status {
    LOADING,
    RESPONSE_SUCCESS,
    RESPONSE_ERROR
}