package com.aldyaz.movix.data.extension

import com.aldyaz.movix.core.exception.HttpException
import com.aldyaz.movix.core.network.HttpResult

suspend inline fun <T : Any> parseHttp(
    crossinline func: suspend () -> T
): HttpResult<T> = try {
    val result = func()
    HttpResult.Success(result)
} catch (err: HttpException) {
    HttpResult.Error(err)
}