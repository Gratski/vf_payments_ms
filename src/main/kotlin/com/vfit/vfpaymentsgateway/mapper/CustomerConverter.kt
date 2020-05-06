package com.vfit.vfpaymentsgateway.mapper

import com.stripe.model.Customer
import com.vfit.vfpaymentsgateway.entities.dto.common.AddressDTO
import com.vfit.vfpaymentsgateway.entities.dto.input.CustomerInputDto
import com.vfit.vfpaymentsgateway.entities.dto.output.CustomerOutputDto
import org.springframework.stereotype.Component
import java.util.*

@Component
class CustomerConverter {

    fun convertToDto(customer: Customer) : CustomerOutputDto {
        val dto = CustomerOutputDto()
        dto.id = customer.id
        dto.name = customer.name
        dto.currency = customer.currency
        dto.email = customer.email
        dto.deleted = customer.deleted
        dto.balance = customer.balance
        dto.description = customer.description
        dto.deleted = customer.deleted
        dto.delinquent = customer.delinquent
        dto.invoicePrefix = customer.invoicePrefix
        dto.phone = customer.phone
        dto.taxExempt = customer.taxExempt
        dto.metadata = customer.metadata

        if(Objects.nonNull(customer.address)){
            val address = customer.address
            val addressDTO = AddressDTO()
            addressDTO.city = address.city
            addressDTO.country = address.country
            addressDTO.line1 = address.line1
            addressDTO.line2 = address.line2
            addressDTO.postalCode = address.postalCode
            addressDTO.state = address.state
            dto.address = addressDTO
        }
        return dto
    }

    fun convertToDto(customers: MutableList<Customer>) : MutableList<CustomerOutputDto> {
        val customersDto = mutableListOf<CustomerOutputDto>()

        customers.stream().forEach { a -> customersDto.add(this.convertToDto(a))}

        return customersDto
    }
}