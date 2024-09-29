package com.aldyaz.movix.ui.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun MoviePoster(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    val painterResource = asyncPainterResource(imageUrl)
    KamelImage(
        resource = painterResource,
        contentDescription = contentDescription,
        onLoading = {
            Box(
                modifier = Modifier
                    .posterSize()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Gray.copy(alpha = 0.5f))
            )
        },
        modifier = Modifier
            .posterSize()
            .defaultPosterAspectRation()
            .clip(RoundedCornerShape(8.dp))
            .then(modifier)
    )
}

private fun Modifier.posterSize() = size(
    width = 120.dp,
    height = 180.dp
)

private fun Modifier.defaultPosterAspectRation() = aspectRatio(2f / 3f)
