package com.example.mygithubtask.common

import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Result.Loadingg(data))

        try {
            saveFetchResult(fetch())
            query().map { Result.Successs(it) }
        } catch (throwable: Throwable) {
            query().map { Result.Errorr(throwable, it) }
        }
    } else {
        query().map { Result.Successs(it) }
    }

    emitAll(flow)
}