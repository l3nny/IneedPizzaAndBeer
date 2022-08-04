package com.example.ineedpizzabeer.utils

sealed class ViewStateResult<out T> {
    data class Success<out T>(val data: T) : ViewStateResult<T>()
    data class Error(val exception: ResultException) : ViewStateResult<Nothing>()
    object Loading : ViewStateResult<Nothing>()
}

inline fun <T : Any> ViewStateResult<T?>.onSuccess(action: (T?) -> Unit): ViewStateResult<T?> {
    if (this is ViewStateResult.Success) action(data)
    return this
}

inline fun <T : Any> ViewStateResult<T?>.onError(action: (ResultException) -> Unit): ViewStateResult<T?> {
    if (this is ViewStateResult.Error) action(exception)
    return this
}

inline fun <T : Any> ViewStateResult<T?>.onLoading(action: () -> Unit): ViewStateResult<T?> {
    if (this is ViewStateResult.Loading) action()
    return this
}