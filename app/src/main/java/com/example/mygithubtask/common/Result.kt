package com.example.mygithubtask.common

sealed class Result<T>
    (
    val data: T? = null,
    val error: Throwable? = null,
) {
    class Successs<T>(data: T) : Result<T>(data)
    class Loadingg<T>(data: T? = null) : Result<T>(data)
    class Errorr<T>(throwable: Throwable, data: T? = null) : Result<T>(data, throwable)
}

