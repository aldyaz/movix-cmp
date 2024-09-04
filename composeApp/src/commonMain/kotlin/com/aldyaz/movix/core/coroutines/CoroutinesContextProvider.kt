package com.aldyaz.movix.core.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

interface CoroutinesContextProvider {

    val io: CoroutineDispatcher
    val main: CoroutineDispatcher

    object Default : CoroutinesContextProvider {

        override val io: CoroutineDispatcher
            get() = Dispatchers.IO

        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
    }

}