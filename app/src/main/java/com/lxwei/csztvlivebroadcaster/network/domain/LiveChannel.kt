package com.lxwei.csztvlivebroadcaster.network.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LiveChannel (
    val id: String,

    val name: String,

    @Json(name = "m3u8")
    val liveStreamUrl: String,

    @Json(name = "audio_only")
    val isAudioOnly: String
)
