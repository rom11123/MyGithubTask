package com.example.mygithubtask.common

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
//    val isLoading: Boolean? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>() : Resource<T>()
}