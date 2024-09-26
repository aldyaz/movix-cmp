package com.aldyaz.movix.ui.main

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainNavigationBar(
    tabs: Array<MainTabType>,
    selected: (MainTabType) -> Boolean,
    onSelectTab: (MainTabType) -> Unit
) {
    NavigationBar {
        tabs.forEach { type ->
            NavigationBarItem(
                selected = selected(type),
                onClick = {
                    onSelectTab(type)
                },
                icon = {
                    Icon(
                        imageVector = type.icon,
                        contentDescription = type.title
                    )
                },
                label = {
                    Text(type.title)
                },
                modifier = Modifier.navigationBarsPadding()
            )
        }
    }
}
