package com.coronaintheworld.remote.common


data class ViewState<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): ViewState<T> {
            return ViewState(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): ViewState<T> {
            return ViewState(Status.ERROR, data, msg)
        }

        fun <T> loading(): ViewState<T> {
            return ViewState(Status.LOADING, null, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}