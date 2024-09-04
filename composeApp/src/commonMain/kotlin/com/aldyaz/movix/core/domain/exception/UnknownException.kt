package com.aldyaz.movix.core.domain.exception

import com.aldyaz.movix.core.domain.exception.DomainException

class UnknownException(
    override val message: String = "Unknown exception!"
) : DomainException()
