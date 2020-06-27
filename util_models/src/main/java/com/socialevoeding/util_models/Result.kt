package com.socialevoeding.util_models

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val throwable: Throwable) : Result<Nothing>()
    data class Loading<out T>(val value: T? = null) : Result<T>()
}