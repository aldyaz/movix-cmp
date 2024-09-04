package com.aldyaz.movix.core.exception

import io.ktor.utils.io.errors.IOException

open class HttpException(
    override val message: String,
    override val cause: Throwable? = null
) : IOException(message, cause) {

    companion object {
        const val DEFAULT_ERROR_MESSAGE = "HTTP exception!"
    }

}