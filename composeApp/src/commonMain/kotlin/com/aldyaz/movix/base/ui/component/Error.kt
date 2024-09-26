package com.aldyaz.movix.base.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun BasicError(
    modifier: Modifier = Modifier,
    onRetryClick: () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "An Error Occured")
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onRetryClick
            ) {
                Text(text = "Try Again")
            }
        }
    }
}

@Preview
@Composable
fun BasicErrorPreview() {
    BasicError(
        modifier = Modifier.aspectRatio(2 / 3f),
        onRetryClick = {}
    )
}