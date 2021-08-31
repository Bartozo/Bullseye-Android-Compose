package com.bartozo.bullseye.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bartozo.bullseye.R


@Composable
fun Button(
    modifier: Modifier = Modifier,
    text: String,
    @DrawableRes icon: Int? = null,
    onClickAction: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .size(width = 100.dp, 40.dp)
            .clickable { onClickAction() },
        backgroundColor = Color.Transparent
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.button),
            contentDescription = "button background image",
        )

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            icon?.let {
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = "Icon"
                )
                Spacer(modifier = Modifier.width(8.dp))
            }

            Text(
                text = text,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = if (icon != null) 12.sp else 18.sp
            )
        }
    }
}


@Preview
@Composable
private fun ButtonPreview() {
    Button(
        text = "Hit me!",
        onClickAction = {}
    )
}

@Preview
@Composable
private fun ButtonWithIconPreview() {
    Button(
        text = "Start over",
        icon = R.drawable.start_over_icon,
        onClickAction = {}
    )
}
