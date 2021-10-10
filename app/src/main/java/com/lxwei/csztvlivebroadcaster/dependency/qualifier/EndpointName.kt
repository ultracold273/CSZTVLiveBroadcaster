package com.lxwei.csztvlivebroadcaster.dependency.qualifier

import com.lxwei.csztvlivebroadcaster.network.domain.Endpoint
import javax.inject.Qualifier

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class EndpointName(val value: Endpoint)
