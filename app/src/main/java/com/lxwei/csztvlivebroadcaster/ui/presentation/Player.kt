package com.lxwei.csztvlivebroadcaster.ui.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.MimeTypes
import com.google.android.exoplayer2.util.Util
import com.lxwei.csztvlivebroadcaster.MainViewModel
import com.lxwei.csztvlivebroadcaster.ui.theme.CSZTVLiveBroadcasterTheme

@Composable
fun LivePlayerScreen(viewModel: MainViewModel) {
    val hlsUrl by viewModel.currentUrl.observeAsState("")
    val close by viewModel.playerClose.observeAsState(false)
    LivePlayer(hlsUrl, close)
}

@Composable
fun LivePlayer(hlsUrl: String, forceClose: Boolean = false) {
    // The official way to access current context from Composable functions
    val context = LocalContext.current

    val exoPlayer = remember {
        SimpleExoPlayer.Builder(context).build()
    }

    LaunchedEffect(hlsUrl, forceClose) {
        if (forceClose) {
            exoPlayer.pause()
        } else {
            val dataSourceFactory = DefaultDataSourceFactory(
                context,
                Util.getUserAgent(context, context.packageName)
            )

            val mediaItem = MediaItem.Builder()
                .setMimeType(MimeTypes.APPLICATION_M3U8)
                .setUri(hlsUrl)
                .build()

            exoPlayer.apply {
                playWhenReady = true

                setMediaSource(
                    HlsMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(mediaItem)
                )

                prepare()
            }
        }
    }

    AndroidView(
        factory = { context ->
            PlayerView(context).apply {
                useController = false
                player = exoPlayer
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CSZTVLiveBroadcasterTheme {
        LivePlayer("http://show.kan0512.com/ncsztv1/playlist.m3u8?_upt=452c98d41633671628")
    }
}