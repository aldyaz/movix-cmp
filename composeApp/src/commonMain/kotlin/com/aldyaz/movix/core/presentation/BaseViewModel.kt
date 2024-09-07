package com.aldyaz.movix.core.presentation

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<Intent> : ViewModel() {

    abstract fun onIntent(intent: Intent)

}
