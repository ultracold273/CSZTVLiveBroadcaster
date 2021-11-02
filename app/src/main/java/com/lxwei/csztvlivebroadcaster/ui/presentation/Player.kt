package com.lxwei.csztvlivebroadcaster.ui.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.lxwei.csztvlivebroadcaster.player.LivePlayer
import com.lxwei.csztvlivebroadcaster.MainViewModel
import com.lxwei.csztvlivebroadcaster.player.PlayerAction

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
    Box {
        Text(text = "You are watching video", modifier = Modifier.align(Alignment.BottomCenter))

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
}