package com.lxwei.csztvlivebroadcaster.network.implementation

import com.lxwei.csztvlivebroadcaster.network.domain.Constants
import com.lxwei.csztvlivebroadcaster.network.domain.RetrofitQueryMap
import javax.inject.Inject

class ChannelServiceQueryProvider @Inject constructor() {
    fun getQueries(): RetrofitQueryMap {
        return mapOf(
            "appid" to Constants.QUERIES_APP_ID,
            "appkey" to Constants.QUERIES_APP_KEY,
            "client_id_android" to Constants.QUERIES_CLIENT_ID,
            "device_token" to Constants.QUERIES_DEVICE_TOKEN,
            "version" to Constants.HEADERS_API_VERSION,
            "app_version" to Constants.HEADERS_API_VERSION,
            "package_name" to Constants.QUERIES_PACKAGE_NAME,
            "system_version" to Constants.QUERIES_SYSTEM_VERSION,
            "phone_models" to Constants.QUERIES_PHONE_MODEL,
            "phyaddr" to Constants.QUERIES_PHY_ADDR,
            "device_id" to Constants.QUERIES_DEVICE_TOKEN,
            "union_id" to "",
            "_member_id" to ""
        )
    }
}