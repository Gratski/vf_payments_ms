package com.vfit.vfpaymentsgateway.services

import com.stripe.Stripe
import org.springframework.beans.factory.annotation.Value
import javax.annotation.PostConstruct

open class StripeSetup {

    @Value("\${vfit.api-key}")
    lateinit var apiKey: String

    @PostConstruct
    fun setup(){
        Stripe.apiKey = apiKey
    }
}