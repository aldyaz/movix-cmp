package com.aldyaz.movix.core.network

import com.aldyaz.movix.core.exception.HttpException

sealed class HttpResult<out T : Any> {

    data class Success<out T : Any>(
        val data: T
    ) : HttpResult<T>()

    data class Error(
        val exception: HttpException
    ) : HttpResult<Nothing>()

}
