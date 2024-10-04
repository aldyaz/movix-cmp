package com.aldyaz.movix.ui.discover.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aldyaz.movix.presentation.model.MovieItemPresentationModel
import com.aldyaz.movix.utils.MovieImageApi

@Composable
fun MovieRowList(
    items: List<MovieItemPresentationModel>,
    onClickItem: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(12.dp)
    ) {
        items(
            items = items,
            key = { item -> item.id }
        ) { item ->
            MovieMainPoster(
                title = item.title,
                imageUrl = MovieImageApi.imageW500Url(item.posterPath),
                rating = item.rating,
                contentDescription = item.title,
                onClick = {
                    onClickItem(item.id)
                },
                modifier = Modifier.width(IntrinsicSize.Min)
            )
        }
    }
}