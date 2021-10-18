package com.lxwei.csztvlivebroadcaster.ui.presentation

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.lxwei.csztvlivebroadcaster.LivePlayer
import com.lxwei.csztvlivebroadcaster.MainViewModel
import com.lxwei.csztvlivebroadcaster.PlayerAction

@Composable
fun LivePlayerScreen(viewModel: MainViewModel, player: LivePlayer) {
    val action by viewModel.playerAction.observeAsState(initial = PlayerAction.EmptyAction)

    LaunchedEffect(action) {
        when (action) {
            is PlayerAction.StopAction -> {
                player.pause()
            }
            is PlayerAction.UpdateAction -> {
                player.play((action as PlayerAction.UpdateAction).url)
            }
            else -> {

            }
        }
    }

    LivePlayerView(player.player)
}

@Composable
fun LivePlayerView(exoPlayer: ExoPlayer) {
    AndroidView(
        factory = { context ->
            PlayerView(context).apply {
                keepScreenOn = true
                useController = false
                player = exoPlayer
            }
        }
    )
}