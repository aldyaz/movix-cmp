package com.aldyaz.movix.ui.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Tv
import androidx.compose.ui.graphics.vector.ImageVector

enum class MainTabType(
    val title: String,
    val icon: ImageVector
) {

    MOVIE("Movie", Icons.Filled.Movie),
    TV("TV", Icons.Filled.Tv)

}