package com.lxwei.csztvlivebroadcaster.dependency.module

import com.lxwei.csztvlivebroadcaster.dependency.qualifier.EndpointName
import com.lxwei.csztvlivebroadcaster.dependency.qualifier.IoDispatcher
import com.lxwei.csztvlivebroadcaster.network.domain.ChannelService
import com.lxwei.csztvlivebroadcaster.network.domain.Endpoint
import com.lxwei.csztvlivebroadcaster.network.domain.INetworkServiceFactory
import com.lxwei.csztvlivebroadcaster.network.domain.IUrlProvider
import com.lxwei.csztvlivebroadcaster.network.implementation.ChannelServiceUrlProvider
import com.lxwei.csztvlivebroadcaster.network.implementation.ReadOnlyCachingFactory
import com.lxwei.csztvlivebroadcaster.network.implementation.RetrofitServiceFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    @EndpointName(Endpoint.Channel)
    internal abstract fun bindUrlProvider(channelServiceUrlProvider: ChannelServiceUrlProvider): IUrlProvider

    companion object {
        @Provides
        @EndpointName(Endpoint.Channel)
        fun provideNetworkServiceFactory(
            @EndpointName(Endpoint.Channel)
            urlProvider: IUrlProvider,
            @IoDispatcher ioDispatcher: CoroutineDispatcher
        ): INetworkServiceFactory {
            return RetrofitServiceFactory(
                urlProvider,
                ioDispatcher
            )
        }

        @Provides
        @Singleton
        fun provideChannelService(
            @EndpointName(Endpoint.Channel)
            serviceFactory: INetworkServiceFactory
        ): ReadOnlyCachingFactory<ChannelService> {
            return ReadOnlyCachingFactory(
                serviceFactory.initializeServiceAsync(ChannelService::class.java)
            )
        }
    }
}