package com.vfit.vfpaymentsgateway.services.products

import com.vfit.vfpaymentsgateway.entities.dto.common.CustomResponseEntity
import com.vfit.vfpaymentsgateway.entities.dto.input.ProductInputDto
import com.vfit.vfpaymentsgateway.entities.dto.output.ProductOutputDto

interface ProductServiceInt {
    fun create(productInputDto: ProductInputDto) : ProductOutputDto
    fun update(productId: String, productInputDto: ProductInputDto) : ProductOutputDto
    fun findById(productId: String) : ProductOutputDto
    fun findAllProducts() : CustomResponseEntity<ProductOutputDto>
    fun delete(productId: String)
}