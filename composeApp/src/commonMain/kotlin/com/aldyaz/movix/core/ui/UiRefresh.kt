package com.aldyaz.movix.core.ui

sealed class UiRefreshAction {

    data object Refreshing : UiRefreshAction()

    data object Idle : UiRefreshAction()

}
