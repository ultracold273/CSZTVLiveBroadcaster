package com.lxwei.csztvlivebroadcaster.network.domain

/**
 * Provides the base URL for HLS service
 */
interface IUrlProvider {
    fun getBaseUrl(): String
}