package com.pg.composenavigation.feature.item3

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pg.composenavigation.ui.theme.ComposeNavigationTheme

@Composable
fun Item3Nested2Content(
    text: String,
    onClickNext: () -> Unit,
    onClickBack: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(imageVector = Icons.Default.FavoriteBorder, contentDescription = null)
            Button(
                onClick = onClickNext
            ) {
                Text(text = "Next")
            }
            Button(
                onClick = onClickBack
            ) {
                Text(text = "Back")
            }
            Text(text = text)
        }
    }
}

@Preview
@Composable
private fun Item3Nested2ContentPreview() {
    ComposeNavigationTheme {
        Item3Nested2Content(
            "Hello World!", {}, {}
        )
    }
}