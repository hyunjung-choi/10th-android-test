package com.prography.android.test.hyunjung.ui.random

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.prography.android.test.hyunjung.R
import com.prography.android.test.hyunjung.ui.theme.White
import com.prography.android.test.hyunjung.ui.theme._10thandroidtestTheme

@Composable
fun RandomScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
    ) {
        Image(
            painter = painterResource(R.drawable.img_random),
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun RandomScreenPreview() {
    _10thandroidtestTheme {
        RandomScreen()
    }
}