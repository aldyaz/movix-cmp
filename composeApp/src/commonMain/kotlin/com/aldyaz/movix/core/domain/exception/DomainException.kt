package com.aldyaz.movix.core.domain.exception

abstract class DomainException(
    cause: Throwable? = null
) : Exception(cause)
