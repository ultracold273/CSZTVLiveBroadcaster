package com.lxwei.csztvlivebroadcaster

import android.content.Context
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.ExoPlaybackException.TYPE_SOURCE
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.MimeTypes
import com.google.android.exoplayer2.util.Util
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject


class LivePlayer @Inject constructor(
    @ApplicationContext private val context: Context,
    private val errorHandler: () -> Unit,
) {
    private val _player = SimpleExoPlayer.Builder(context).build().apply {
        addListener (
            object : Player.Listener {
                override fun onPlayerError(error: PlaybackException) {
                    if (error is ExoPlaybackException && error.type == TYPE_SOURCE) {
                        errorHandler.invoke()
                        return
                    }
                    super.onPlayerError(error)
                }
            }
        )
    }

    val player = _player

    fun pause() {
        _player.pause()
    }

    fun play(uri: String) {
        val dataSourceFactory = DefaultDataSourceFactory(
            context,
            Util.getUserAgent(context, context.packageName)
        )

        val mediaItem = MediaItem.Builder()
            .setMimeType(MimeTypes.APPLICATION_M3U8)
            .setUri(uri)
            .build()

        _player.apply {
            playWhenReady = true

            setMediaSource(
                HlsMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(mediaItem)
            )

            prepare()
        }

        Timber.i("I prepared the source")
    }
}