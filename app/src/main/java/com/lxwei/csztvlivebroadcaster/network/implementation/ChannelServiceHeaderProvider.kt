package com.lxwei.csztvlivebroadcaster.network.implementation

import com.lxwei.csztvlivebroadcaster.network.domain.Constants
import com.lxwei.csztvlivebroadcaster.network.domain.RetrofitHeaderMap
import javax.inject.Inject

class ChannelServiceHeaderProvider @Inject constructor() {
    fun getHeaders(): RetrofitHeaderMap {
        val timeStamp = Utils.getTimeStamp()
        return mapOf(
            "User-Agent" to Constants.HEADERS_USER_AGNET,
            "X-API-TIMESTAMP" to timeStamp,
            "X-API-VERSION" to Constants.HEADERS_API_VERSION,
            "X-API-SIGNATURE" to Utils.getSignature(Constants.HEADERS_API_VERSION, timeStamp),
            "X-AUTH-TYPE" to Constants.HEADERS_AUTH_TYPE,
            "X-API-KEY" to Constants.HEADERS_API_KEY
        )
    }
}