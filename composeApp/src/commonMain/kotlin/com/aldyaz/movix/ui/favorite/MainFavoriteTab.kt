package com.aldyaz.movix.ui.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aldyaz.movix.presentation.model.MovieItemPresentationModel
import com.aldyaz.movix.presentation.state.MainFavoriteTabState
import com.aldyaz.movix.presentation.viewmodel.MainFavoriteTabViewModel
import com.aldyaz.movix.ui.common.component.RatingBar
import com.aldyaz.movix.ui.theme.colorRating
import com.aldyaz.movix.utils.MovieImageApi
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainFavoriteTab(
    modifier: Modifier = Modifier,
    viewModel: MainFavoriteTabViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    MainFavoriteTabScaffold(
        uiState = state,
        modifier = modifier
    )
}

@Composable
fun MainFavoriteTabScaffold(
    uiState: MainFavoriteTabState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        content = { contentPadding ->
            MainFavoriteTabContent(
                uiState = uiState,
                modifier = Modifier.padding(contentPadding)
            )
        }
    )
}

@Composable
fun MainFavoriteTabContent(
    uiState: MainFavoriteTabState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = modifier
    ) {
        items(
            items = uiState.movies,
            key = { movie -> movie.id },
            itemContent = { movie ->
                FavoriteMovieItem(
                    movie = movie,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            }
        )
    }
}

@Composable
fun FavoriteMovieItem(
    movie: MovieItemPresentationModel,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = {},
        modifier = modifier,
        content = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                KamelImage(
                    resource = asyncPainterResource(
                        MovieImageApi.imageW500Url(movie.posterPath)
                    ),
                    contentDescription = movie.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .aspectRatio(4f / 3f)
                        .clip(CardDefaults.shape)
                        .weight(0.4f)
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.weight(0.6f)
                ) {
                    Text(
                        text = movie.title,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(16.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = movie.rating.toString(),
                            style = MaterialTheme.typography.titleLarge.copy(
                                color = colorRating,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(Modifier.width(8.dp))
                        RatingBar(
                            rating = movie.ratingStar,
                            modifier = Modifier.height(16.dp)
                        )
                    }
                }
            }
        }
    )
}
