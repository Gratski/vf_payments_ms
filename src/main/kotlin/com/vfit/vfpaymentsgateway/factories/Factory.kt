package com.vfit.vfpaymentsgateway.factories

import com.stripe.model.Address
import com.stripe.model.Customer
import com.stripe.param.CustomerCreateParams
import com.stripe.param.CustomerUpdateParams
import org.springframework.stereotype.Component

@Component
class Factory : FactoryInf<Customer, CustomerCreateParams, CustomerUpdateParams> {

    override fun toCreate(customer: Customer): CustomerCreateParams {
        val address = this.transformCreateAddres(customer.address)

        return CustomerCreateParams.builder()
                .setName(customer.name)
                .setEmail(customer.email)
                .setDescription(customer.description)
                .setAddress(address)
                .build()
    }

    override fun toUpdate(customer: Customer): CustomerUpdateParams {
        val address = this.transformUpdateAddres(customer.address)

        return CustomerUpdateParams.builder()
                .setName(customer.name)
                .setEmail(customer.email)
                .setDescription(customer.description)
                .setAddress(address)
                .build()
    }

    private fun transformCreateAddres(address : Address): CustomerCreateParams.Address{
        return CustomerCreateParams.Address.builder()
                .setCountry(address.country)
                .setCity(address.city)
                .setLine1(address.line1)
                .setLine2(address.line2)
                .setPostalCode(address.postalCode)
                .setState(address.state)
                .build()
    }

    private fun transformUpdateAddres(address : Address): CustomerUpdateParams.Address{
        return CustomerUpdateParams.Address.builder()
                .setCountry(address.country)
                .setCity(address.city)
                .setLine1(address.line1)
                .setLine2(address.line2)
                .setPostalCode(address.postalCode)
                .setState(address.state)
                .build()
    }

}