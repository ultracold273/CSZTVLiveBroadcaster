package com.lxwei.csztvlivebroadcaster.network.domain

import kotlinx.coroutines.Deferred

interface INetworkServiceFactory {
    fun <T> initializeServiceAsync(clazz: Class<T>): Deferred<T>
}