package com.aldyaz.movix.core.domain

abstract class UseCase<PARAM, RESULT> {

    abstract suspend fun execute(param: PARAM): ResultState<RESULT>

    suspend operator fun invoke(param: PARAM) = execute(param)

}