package com.vfit.vfpaymentsgateway.mapper

import com.stripe.model.Customer
import com.stripe.param.CustomerCreateParams
import com.vfit.vfpaymentsgateway.entities.dto.input.CustomerInputDto
import com.vfit.vfpaymentsgateway.entities.dto.output.CustomerOutputDto
import org.mapstruct.Mapper

@Mapper
interface CustomerMapper {
    //fun convertInputDtoToCreateParams(inputDto: CustomerInputDto) : CustomerCreateParams
    fun convertEntityToOutputDTO(customer: Customer) : CustomerOutputDto
    fun convertEntityListToOutputDtoList(customers: MutableList<Customer>) : MutableList<CustomerOutputDto>
}