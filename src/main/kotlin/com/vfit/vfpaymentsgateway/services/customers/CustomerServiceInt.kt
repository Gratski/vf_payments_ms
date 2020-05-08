package com.vfit.vfpaymentsgateway.services.customers

import com.vfit.vfpaymentsgateway.entities.dto.input.CustomerInputDto
import com.vfit.vfpaymentsgateway.entities.dto.common.CustomResponseEntity
import com.vfit.vfpaymentsgateway.entities.dto.output.CustomerOutputDto

interface CustomerServiceInt {
    fun create(customerInputDto: CustomerInputDto): CustomerOutputDto
    fun findByName(name: String): CustomResponseEntity<CustomerOutputDto>
    fun findByEmail(email: String): CustomerOutputDto
    fun update(email: String, customerInputDto: CustomerInputDto): CustomerOutputDto
}