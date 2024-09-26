package com.aldyaz.movix.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.aldyaz.movix.ui.main.MainTabType

class MainViewModel : ViewModel() {

    private val _selectedTab: MutableState<MainTabType> = mutableStateOf(MainTabType.MOVIE)
    val selectedTab: State<MainTabType>
        get() = _selectedTab

    fun selectTab(tab: MainTabType) {
        _selectedTab.value = tab
    }

}