package com.lxwei.csztvlivebroadcaster.network.domain

import kotlinx.coroutines.Deferred

interface INetworkServiceFactory {
    fun <T> initializeService(clazz: Class<T>): Deferred<T>
}