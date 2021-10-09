package com.lxwei.csztvlivebroadcaster

import com.lxwei.csztvlivebroadcaster.network.domain.ChannelService
import com.lxwei.csztvlivebroadcaster.network.implementation.Utils
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import javax.inject.Inject

class ChannelInfoRepo
@Inject constructor(
) {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://mobile.kan0512.com/")
        .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
        .build()

    val service = retrofit.create(ChannelService::class.java)

    suspend fun getLiveChannels(): List<String> {
        val timeStamp = Utils.getTimeStamp()
        val response = service.getLiveChannels(
            headers = mapOf(
                "User-Agent" to "Dalvik/2.1.0 (Linux; U; Android 7.1.2; Redmi 4A MIUI/V10.2.3.0.NCCMIXM) m2oSmartCity_useragent 1.0.0",
                "X-API-TIMESTAMP" to timeStamp,
                "X-API-VERSION" to "8.3.1",
                "X-API-SIGNATURE" to Utils.getSignature("8.3.1", timeStamp),
                "X-AUTH-TYPE" to "sha1",
                "X-API-KEY" to "eecca5b6365d9607ee5a9d336962c534"
            ),
            queries = mapOf(
                "appid" to "37",
                "appkey" to "BHAk5KonEtoiZfqw4SW9taIYZF8NLxId",
                "client_id_android" to "610bb8725b2a289c44bf75716eb1369f",
                "device_token" to "8584b24198f13c3b3502e4a7226082b1",
                "version" to "8.3.1",
                "app_version" to "8.3.1",
                "package_name" to "com.csztv.yyk",
                "system_version" to "7.1.2",
                "phone_models" to "Redmi4A",
                "phyaddr" to "fe80::64b7:aeff:fe6d:d912%dummy0",
                "device_id" to "8584b24198f13c3b3502e4a7226082b1",
                "union_id" to "",
                "_member_id" to ""
            )
        )

        Timber.i("Channels Length ${response.size}")
        Timber.i("StreamUrl: $response")

        return response.mapNotNull { channel ->
            if (channel.isAudioOnly == "0" && channel.id != "57") channel.liveStreamUrl else null
        }
    }
}