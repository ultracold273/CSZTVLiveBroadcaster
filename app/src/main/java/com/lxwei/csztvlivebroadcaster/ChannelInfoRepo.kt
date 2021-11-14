package com.lxwei.csztvlivebroadcaster

import com.lxwei.csztvlivebroadcaster.network.domain.ChannelService
import com.lxwei.csztvlivebroadcaster.network.implementation.ChannelServiceHeaderProvider
import com.lxwei.csztvlivebroadcaster.network.implementation.ChannelServiceQueryProvider
import com.lxwei.csztvlivebroadcaster.network.implementation.ReadOnlyCachingFactory
import timber.log.Timber
import javax.inject.Inject

class ChannelInfoRepo
@Inject constructor(
    private val service: ReadOnlyCachingFactory<ChannelService>,
    private val headerProvider: ChannelServiceHeaderProvider,
    private val queryProvider: ChannelServiceQueryProvider
) {
    suspend fun getLiveChannels(): List<String> {
        Timber.i("Updating Channels")
        val response = service.getValue().getLiveChannels(
            headerProvider.getHeaders(),
            queryProvider.getQueries()
        )

        Timber.i("Channels Length ${response.size}")
        Timber.i("StreamUrl: $response")

        return response.mapNotNull { channel ->
            if (channel.isAudioOnly == "0" && channel.id != "57") channel.liveStreamUrl else null
        }
    }
}