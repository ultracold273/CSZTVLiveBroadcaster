package com.lxwei.csztvlivebroadcaster.network.implementation

import kotlinx.coroutines.Deferred

class ReadOnlyCachingFactory<T>(private var cachedValueDeferred: Deferred<T>) {
    suspend fun getValue(): T {
        return cachedValueDeferred.await()
    }
}