package com.pg.composenavigation.feature.item3

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pg.composenavigation.R
import com.pg.composenavigation.ui.theme.ComposeNavigationTheme

@Composable
fun Item3Content(
    onClickNext: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(fraction = 0.5f),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(id = R.drawable.iu3),
                contentDescription = "Bottom navigation item3 content."
            )
            Button(
                onClick = onClickNext
            ) {
                Text(text = "Next")
            }
        }
    }
}

@Preview
@Composable
private fun Item1ContentPreview() {
    ComposeNavigationTheme {
        Item3Content {}
    }
}