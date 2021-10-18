package com.pg.composenavigation.feature.item1

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pg.composenavigation.R
import com.pg.composenavigation.ui.theme.ComposeNavigationTheme

@Composable
fun Item1Content() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painterResource(id = R.drawable.iu),
            contentDescription = "Bottom navigation item1 content."
        )
    }
}

@Preview
@Composable
private fun Item1ContentPreview() {
    ComposeNavigationTheme {
        Item1Content()
    }
}