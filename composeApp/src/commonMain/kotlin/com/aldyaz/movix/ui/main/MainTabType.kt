package com.aldyaz.movix.ui.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.aldyaz.movix.navigation.MainScreen
import com.slack.circuit.runtime.screen.Screen

enum class MainTabType(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
) {

    HOME(
        title = "Home",
        icon = Icons.Filled.Home,
        screen = MainScreen.HomeTab
    ),
    SEARCH(
        title = "Search",
        icon = Icons.Filled.Search,
        screen = MainScreen.SearchTab
    ),
    FAVORITE(
        title = "Favorite",
        icon = Icons.Filled.Bookmark,
        screen = MainScreen.FavoriteTab
    )

}