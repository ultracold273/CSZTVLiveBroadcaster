package com.lxwei.csztvlivebroadcaster.network.implementation

import com.lxwei.csztvlivebroadcaster.network.domain.GetChannelConstants
import com.lxwei.csztvlivebroadcaster.network.domain.RetrofitQueryMap
import javax.inject.Inject

class ChannelServiceQueryProvider @Inject constructor() {
    fun getQueries(): RetrofitQueryMap {
        return mapOf(
            "appid" to GetChannelConstants.QUERIES_APP_ID,
            "appkey" to GetChannelConstants.QUERIES_APP_KEY,
            "client_id_android" to GetChannelConstants.QUERIES_CLIENT_ID,
            "device_token" to GetChannelConstants.QUERIES_DEVICE_TOKEN,
            "version" to GetChannelConstants.HEADERS_API_VERSION,
            "app_version" to GetChannelConstants.HEADERS_API_VERSION,
            "package_name" to GetChannelConstants.QUERIES_PACKAGE_NAME,
            "system_version" to GetChannelConstants.QUERIES_SYSTEM_VERSION,
            "phone_models" to GetChannelConstants.QUERIES_PHONE_MODEL,
            "phyaddr" to GetChannelConstants.QUERIES_PHY_ADDR,
            "device_id" to GetChannelConstants.QUERIES_DEVICE_TOKEN,
            "union_id" to "",
            "_member_id" to ""
        )
    }
}