package com.vfit.vfpaymentsgateway.services

import com.stripe.model.Customer
import com.stripe.param.CustomerCreateParams
import com.stripe.param.CustomerListParams
import com.stripe.param.CustomerUpdateParams
import com.vfit.vfpaymentsgateway.exceptions.EmailExistsException
import com.vfit.vfpaymentsgateway.factories.FactoryInf
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerService(val factory : FactoryInf<Customer, CustomerCreateParams, CustomerUpdateParams>) : StripeSetup(), CustomerServiceInt {

    override fun create(customer: Customer): Customer{

        val customerCreateParams = factory.toCreate(customer)

        return Customer.create(customerCreateParams)
    }

    @Deprecated("Just to see how call a object properties")
    override fun findByName(name: String): MutableList<Customer>? {
        val metadata: MutableMap<String, String> = HashMap()
        metadata["name"] = "Divanio Silva"

        val params: MutableMap<String, Any> = HashMap()
        params["metadata"] = metadata

        val account: MutableList<Customer>? = Customer.list(params).data
        return account
    }

    override fun findByEmail(email: String): Customer {
        var query = CustomerListParams.builder().setEmail(email).build()
        val result: MutableList<Customer>? = Customer.list(query).data
        lateinit var customer: Customer
        if(!result.isNullOrEmpty()){
            if(result.size > 1){
                throw EmailExistsException(email)
            }
            customer = result.first()
        }
        return customer
    }

    override fun update(email: String, customer: Customer): Customer {
        val searchCustomerByEmailResult= this.findByEmail(email)
        val customerUpdateParams = this.factory.toUpdate(customer)
        return customer.update(customerUpdateParams)
    }
}