package com.bartozo.bullseye.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Label(
    text: String,
    value: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 18.sp
        )
        Spacer(modifier = modifier.width(8.dp))
        Text(
            text = value.toString(),
            color = Color.Yellow,
            fontSize = 24.sp
        )
    }
}

@Preview
@Composable
private fun LabelPreview() {
    Label("Score:",0)
}
