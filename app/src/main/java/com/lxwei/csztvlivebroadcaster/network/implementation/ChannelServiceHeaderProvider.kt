package com.lxwei.csztvlivebroadcaster.network.implementation

import com.lxwei.csztvlivebroadcaster.network.domain.GetChannelConstants
import com.lxwei.csztvlivebroadcaster.network.domain.RetrofitHeaderMap
import javax.inject.Inject

class ChannelServiceHeaderProvider @Inject constructor() {
    fun getHeaders(): RetrofitHeaderMap {
        val timeStamp = Utils.getTimeStamp()
        return mapOf(
            "User-Agent" to GetChannelConstants.HEADERS_USER_AGNET,
            "X-API-TIMESTAMP" to timeStamp,
            "X-API-VERSION" to GetChannelConstants.HEADERS_API_VERSION,
            "X-API-SIGNATURE" to Utils.getSignature(GetChannelConstants.HEADERS_API_VERSION, timeStamp),
            "X-AUTH-TYPE" to GetChannelConstants.HEADERS_AUTH_TYPE,
            "X-API-KEY" to GetChannelConstants.HEADERS_API_KEY
        )
    }
}