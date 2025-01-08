package com.aldyaz.movix.core.ui

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed class UiResult<out T> {
    data object Loading : UiResult<Nothing>()
    data class Error(
        val err: Throwable
    ) : UiResult<Nothing>()

    data class Success<T>(val data: T) : UiResult<T>()
}

fun <T> Flow<T>.toUiResult(): Flow<UiResult<T>> = map<T, UiResult<T>> {
    UiResult.Success(it)
}.onStart {
    emit(UiResult.Loading)
}.catch {
    emit(UiResult.Error(it))
}
