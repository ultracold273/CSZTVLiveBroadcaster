package com.lxwei.csztvlivebroadcaster.network.implementation

import com.squareup.moshi.Moshi

object DefaultMoshi {
    val instance: Moshi = Moshi.Builder().build()
}