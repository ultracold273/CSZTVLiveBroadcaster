package com.lxwei.csztvlivebroadcaster.ui.presentation

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.lxwei.csztvlivebroadcaster.LivePlayer
import com.lxwei.csztvlivebroadcaster.MainViewModel
import timber.log.Timber

@Composable
fun LivePlayerScreen(viewModel: MainViewModel, player: LivePlayer) {
    val hlsUrl by viewModel.currentUrl.observeAsState("")
    val close by viewModel.playerClose.observeAsState(false)

    LaunchedEffect(hlsUrl, close) {
        if (close) player.pause()
        else player.play(hlsUrl)
    }

    LivePlayerView(player.player)
}

@Composable
fun LivePlayerView(exoPlayer: ExoPlayer) {
    AndroidView(
        factory = { context ->
            PlayerView(context).apply {
                useController = false
                player = exoPlayer
            }
        }
    )
}