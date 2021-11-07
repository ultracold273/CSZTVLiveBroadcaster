package com.lxwei.csztvlivebroadcaster.network.implementation

import com.lxwei.csztvlivebroadcaster.network.domain.GetChannelConstants
import com.lxwei.csztvlivebroadcaster.network.domain.IUrlProvider
import javax.inject.Inject

class ChannelServiceUrlProvider @Inject constructor() : IUrlProvider {
    override fun getBaseUrl(): String = GetChannelConstants.CHANNEL_URL
}