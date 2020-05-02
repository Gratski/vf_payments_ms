package com.vfit.vfpaymentsgateway.services

import com.stripe.Stripe
import com.stripe.model.Customer
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerService : StripeSetup() {

    fun create(): Customer{

        val metadata: MutableMap<String, String> = HashMap()
        metadata["name"] = "Divanio Silva"

        val params: MutableMap<String, Any> = HashMap()
        params["description"] = "My First Test Customer (created for API docs)"
        params["email"] = "dcsilva2@mail.ru"
        params["metadata"] = metadata

        val customer = Customer.create(params)
        return customer
    }

    fun findByName(name: String): MutableList<Customer>? {

        val metadata: MutableMap<String, String> = HashMap()
        metadata["name"] = "Divanio Silva"

        val params: MutableMap<String, Any> = HashMap()
        params["metadata"] = metadata

        val account: MutableList<Customer>? = Customer.list(params).data
        return account
    }

    fun findByEmail(email: String): MutableList<Customer>? {
        Stripe.apiKey = "sk_test_eMJA07HTa4ephNA3lR42CuHG00k6gjNgTz"

        val params: MutableMap<String, Any> = HashMap()
        params["email"] = email

        val account: MutableList<Customer>? = Customer.list(params).data
        return account
    }
}