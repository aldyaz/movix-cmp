package com.aldyaz.movix.ui.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

enum class MainTabType(
    val title: String,
    val icon: ImageVector
) {

    HOME("Home", Icons.Filled.Home),
    SEARCH("Search", Icons.Filled.Search),
    FAVORITE("Favorite", Icons.Filled.Favorite)

}