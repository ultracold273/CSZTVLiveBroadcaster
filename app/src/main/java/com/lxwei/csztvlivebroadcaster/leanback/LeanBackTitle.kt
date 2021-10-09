package com.lxwei.csztvlivebroadcaster.leanback

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LeanBackTitle(
    text: String?
) {
    Box(
        modifier = Modifier.wrapContentWidth()
    ) {
        Text(
            text!!,
            modifier = Modifier.height(TITLE_TEXT_HEIGHT)
        )
    }
}

@Preview
@Composable
fun Title() {
    LeanBackTitle("CSZTV")
}