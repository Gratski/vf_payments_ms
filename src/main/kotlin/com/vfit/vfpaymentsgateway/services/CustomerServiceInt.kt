package com.vfit.vfpaymentsgateway.services

import com.stripe.model.Customer

interface CustomerServiceInt {
    fun create(customer: Customer): Customer
    fun findByName(name: String): MutableList<Customer>?
    fun findByEmail(email: String): Customer
    fun update(email: String, customer: Customer): Customer
}