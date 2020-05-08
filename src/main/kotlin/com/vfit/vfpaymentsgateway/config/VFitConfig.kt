package com.vfit.vfpaymentsgateway.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import kotlin.properties.Delegates

@Configuration
@ConfigurationProperties(prefix = "vfit")
open class VFitConfig (){
    var liveMode by Delegates.notNull<Boolean>()
}