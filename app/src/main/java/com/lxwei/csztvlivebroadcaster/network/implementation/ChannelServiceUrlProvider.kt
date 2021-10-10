package com.lxwei.csztvlivebroadcaster.network.implementation

import com.lxwei.csztvlivebroadcaster.network.domain.Constants
import com.lxwei.csztvlivebroadcaster.network.domain.IUrlProvider
import javax.inject.Inject

class ChannelServiceUrlProvider @Inject constructor() : IUrlProvider {
    override fun getBaseUrl(): String = Constants.CHANNEL_URL
}