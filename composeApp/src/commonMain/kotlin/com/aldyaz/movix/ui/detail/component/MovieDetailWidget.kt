package com.aldyaz.movix.ui.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.aldyaz.movix.ui.common.component.BaseChip
import com.aldyaz.movix.utils.MovieImageApi
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import movixcmp.composeapp.generated.resources.Res
import movixcmp.composeapp.generated.resources.label_overview
import org.jetbrains.compose.resources.stringResource

@Composable
fun BackdropPoster(
    path: String,
    modifier: Modifier = Modifier
) {
    val painterResource = asyncPainterResource(
        data = MovieImageApi.imageW780Url(path)
    )
    Box(
        modifier = modifier
    ) {
        KamelImage(
            resource = painterResource,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            onLoading = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(4f / 3f)
                        .background(Color.Gray.copy(alpha = 0.5f))
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(4f / 3f)
        )
    }
}

@Composable
fun TitleSection(
    title: String,
    favorite: Boolean,
    onClickFavorite: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(
            text = title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(end = 8.dp)
                .width(IntrinsicSize.Min)
                .weight(1f)
        )
        IconButton(
            onClick = onClickFavorite,
            content = {
                Icon(
                    imageVector = if (favorite) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                    contentDescription = null
                )
            }
        )
    }
}

@Composable
fun RatingSection(
    rating: Double,
    releaseDate: String,
    showTimeDuration: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier.fillMaxWidth()
    ) {
        VerticalTextIcon(
            icon = Icons.Filled.Star,
            text = "$rating/10"
        )
        VerticalTextIcon(
            icon = Icons.Filled.CalendarToday,
            text = releaseDate
        )
        VerticalTextIcon(
            icon = Icons.Filled.AccessTime,
            text = showTimeDuration
        )
    }
}

@Composable
fun OverviewSection(
    overview: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(Res.string.label_overview),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        ExpandedText(
            text = overview,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun ExpandedText(
    text: String,
    modifier: Modifier = Modifier,
    expandable: Boolean = true,
    collapsedMaxLines: Int = 4,
    expandedMaxLines: Int = Int.MAX_VALUE,
    style: TextStyle = LocalTextStyle.current
) {
    var canTextExpandable by remember(text) {
        mutableStateOf(true)
    }
    var expanded by remember {
        mutableStateOf(false)
    }

    Text(
        text = text,
        maxLines = if (expanded) expandedMaxLines else collapsedMaxLines,
        overflow = TextOverflow.Ellipsis,
        onTextLayout = {
            if (!expanded) {
                canTextExpandable = it.hasVisualOverflow
            }
        },
        textAlign = TextAlign.Justify,
        style = style,
        modifier = Modifier
            .clickable(
                enabled = expandable && canTextExpandable,
                onClick = {
                    expanded = !expanded
                }
            )
            .then(modifier)
    )
}

@Composable
fun GenresHorizontalScrollable(
    genres: List<String>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
    ) {
        itemsIndexed(genres) { index, item ->
            BaseChip(
                category = item,
                modifier = Modifier.padding(
                    start = if (index == 0) 16.dp else 4.dp,
                    end = if (index == genres.size - 1) 16.dp else 4.dp
                )
            )
        }
    }
}

@Composable
fun GeneralInformationSection(
    originalLanguage: Pair<String, String>,
    budget: Pair<String, String>,
    revenue: Pair<String, String>,
    releasedStatus: Pair<String, String>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "General Information",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(Modifier.height(16.dp))
            HorizontalText(
                textPair = originalLanguage,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            HorizontalText(
                textPair = budget,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            HorizontalText(
                textPair = revenue,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            HorizontalText(
                textPair = releasedStatus,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
private fun HorizontalText(
    textPair: Pair<String, String>,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(
            text = textPair.first,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(0.5f)
        )
        Text(
            text = textPair.second,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(
                    start = 8.dp
                )
                .weight(0.5f)
        )
    }
}

@Composable
private fun VerticalTextIcon(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
    iconTint: Color = LocalContentColor.current,
    textStyle: TextStyle = MaterialTheme.typography.bodySmall
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = iconTint,
            modifier = Modifier.size(28.dp)
        )
        Text(
            text = text,
            style = textStyle,
            maxLines = 2,
            modifier = Modifier.padding(
                top = 8.dp
            )
        )
    }
}
