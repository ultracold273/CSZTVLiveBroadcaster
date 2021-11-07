package com.lxwei.csztvlivebroadcaster.network.implementation

import com.lxwei.csztvlivebroadcaster.network.domain.INetworkServiceFactory
import com.lxwei.csztvlivebroadcaster.network.domain.IUrlProvider
import com.squareup.moshi.Moshi
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitServiceFactory constructor(
    private val urlProvider: IUrlProvider,
    private val ioDispatcher: CoroutineDispatcher,
    private val moshi: Moshi = DefaultMoshi.instance
) : INetworkServiceFactory {

    /**
     *
     */
    override fun <T> initializeServiceAsync(clazz: Class<T>): Deferred<T> {
        return CoroutineScope(ioDispatcher).async(start = CoroutineStart.LAZY) {
            initializeRetrofit(urlProvider.getBaseUrl()).create(clazz)
        }
    }

    /**
     *
     */
    private fun initializeRetrofit(baseUrl: String): Retrofit {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))

        return retrofitBuilder.build()
    }
}