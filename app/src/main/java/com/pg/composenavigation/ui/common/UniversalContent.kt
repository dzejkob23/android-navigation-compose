package com.pg.composenavigation.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SimpleContent(text: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text)
    }
}

@Composable
fun SimpleButtonContent(
    text: String,
    onClickNext: () -> Unit,
    onClickBack: () -> Unit,
    route: String
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
            Text(text = text)
            Button(
                onClick = onClickNext
            ) {
                Text(text = "Move to next")
            }
            Button(
                onClick = onClickBack
            ) {
                Text(text = "Move back")
            }
            Text(route)
        }
    }
}