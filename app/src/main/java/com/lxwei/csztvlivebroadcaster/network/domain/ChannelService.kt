package com.lxwei.csztvlivebroadcaster.network.domain

import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.QueryMap

interface ChannelService {
    @GET("szh/channel.php")
    suspend fun getLiveChannels(
        @HeaderMap headers: Map<String, String>,
        @QueryMap queries: Map<String, String>
    ): List<LiveChannel>
}