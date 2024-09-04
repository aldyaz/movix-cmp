package com.aldyaz.movix.core.network

import com.aldyaz.movix.core.exception.BadRequestException
import com.aldyaz.movix.core.exception.HttpException
import com.aldyaz.movix.core.exception.InternalServerException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import io.ktor.utils.io.errors.IOException

suspend inline fun <reified T> apiCall(
    call: () -> HttpResponse
): T = try {
    val response = call()
    if (response.status.isSuccess()) {
        response.body<T>()
    } else {
        val description = response.status.description
        throw when (response.status) {
            HttpStatusCode.BadRequest -> HttpException(
                message = description,
                cause = BadRequestException(description)
            )

            HttpStatusCode.InternalServerError -> HttpException(
                message = description,
                cause = InternalServerException(description)
            )

            else -> HttpException(description)
        }
    }
} catch (e: IOException) {
    throw HttpException(
        message = e.message?.let {
            it.ifEmpty { HttpException.DEFAULT_ERROR_MESSAGE }
        } ?: HttpException.DEFAULT_ERROR_MESSAGE,
        cause = e
    )
}