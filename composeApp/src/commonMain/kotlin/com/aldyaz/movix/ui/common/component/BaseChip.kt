package com.aldyaz.movix.ui.common.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun BaseChip(
    category: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = category,
        color = MaterialTheme.colorScheme.secondary,
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(
                vertical = 2.dp,
                horizontal = 4.dp
            )
    )
}

@Preview
@Composable
fun BaseChipPreview() {
    BaseChip(category = "Action")
}
