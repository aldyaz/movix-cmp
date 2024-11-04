package com.aldyaz.movix.ui.favorite

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aldyaz.movix.presentation.viewmodel.MainFavoriteTabViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainFavoriteTab(
    modifier: Modifier = Modifier,
    viewModel: MainFavoriteTabViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            items = state.movies,
            key = { it.id }
        ) {
            androidx.compose.material.Text(
                text = it.title,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
