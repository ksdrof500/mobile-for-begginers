package com.coronaintheworld.remote.common

import retrofit2.HttpException
import java.net.SocketTimeoutException

enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1)
}

open class ResponseHandler {
    fun <T : Any> handleSuccess(data: T): ViewState<T> {
        return ViewState.success(data)
    }

    fun <T : Any> handleException(e: Exception): ViewState<T> {
        return when (e) {
            is HttpException -> ViewState.error(getErrorMessage(e.code()), null)
            is SocketTimeoutException -> ViewState.error(getErrorMessage(ErrorCodes.SocketTimeOut.code), null)
            else -> ViewState.error(getErrorMessage(Int.MAX_VALUE), null)
        }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            ErrorCodes.SocketTimeOut.code -> "Timeout"
            401 -> "Unauthorised"
            404 -> "Not found"
            else -> "Something went wrong"
        }
    }
}