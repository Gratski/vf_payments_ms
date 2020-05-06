package com.vfit.vfpaymentsgateway.factories

import com.stripe.param.CustomerCreateParams
import com.stripe.param.CustomerUpdateParams
import com.vfit.vfpaymentsgateway.entities.dto.common.AddressDTO
import com.vfit.vfpaymentsgateway.entities.dto.input.CustomerInputDto
import org.springframework.stereotype.Component

@Component
class Factory : FactoryInf<CustomerInputDto, CustomerCreateParams, CustomerUpdateParams> {

    override fun toCreate(customerInputDto: CustomerInputDto): CustomerCreateParams {
        val address = customerInputDto.address?.let { this.transformCreateAddress(it) }

        return CustomerCreateParams.builder()
                .setName(customerInputDto.name)
                .setEmail(customerInputDto.email)
                .setDescription(customerInputDto.description)
                .setAddress(address)
                .setPhone(customerInputDto.phone)
                .setMetadata(customerInputDto.metadata)
                .build()
    }

    override fun toUpdate(customerInputDto: CustomerInputDto): CustomerUpdateParams {
        val address = customerInputDto.address?.let { this.transformUpdateAddress(it) }

        return CustomerUpdateParams.builder()
                .setName(customerInputDto.name)
                .setEmail(customerInputDto.email)
                .setDescription(customerInputDto.description)
                .setAddress(address)
                .setPhone(customerInputDto.phone)
                .setMetadata(customerInputDto.metadata)
                .build()
    }

    private fun transformCreateAddress(address: AddressDTO): CustomerCreateParams.Address{
        return CustomerCreateParams.Address.builder()
                .setCountry(address.country)
                .setCity(address.city)
                .setLine1(address.line1)
                .setLine2(address.line2)
                .setPostalCode(address.postalCode)
                .setState(address.state)
                .build()
    }

    private fun transformUpdateAddress(address : AddressDTO): CustomerUpdateParams.Address{
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