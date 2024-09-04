package com.aldyaz.movix.domain.mapper

import com.aldyaz.movix.core.domain.exception.CloudApiException
import com.aldyaz.movix.core.domain.exception.DomainException
import com.aldyaz.movix.core.domain.exception.UnknownException
import com.aldyaz.movix.core.exception.BadRequestException
import com.aldyaz.movix.core.exception.HttpException
import com.aldyaz.movix.core.exception.InternalServerException

class HttpExceptionToDomainMapper : (Exception) -> DomainException {

    override fun invoke(p1: Exception): DomainException {
        return when (p1) {
            is HttpException -> {
                p1.cause?.let {
                    when (it) {
                        is BadRequestException -> CloudApiException(it.message)
                        is InternalServerException -> CloudApiException(it.message)
                        else -> UnknownException()
                    }
                } ?: UnknownException()
            }

            else -> UnknownException()
        }
    }
}