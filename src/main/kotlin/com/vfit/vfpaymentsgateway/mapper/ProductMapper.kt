package com.vfit.vfpaymentsgateway.mapper

import com.stripe.model.Product
import com.stripe.param.ProductCreateParams
import com.vfit.vfpaymentsgateway.entities.dto.input.ProductInputDto
import com.vfit.vfpaymentsgateway.entities.dto.output.ProductOutputDto
import org.mapstruct.Mapper

@Mapper
interface ProductMapper {
    fun convertInputDtoToProductCreateParams(productInputDto: ProductInputDto) : ProductCreateParams
    fun convertProductToOutputDTO(product: Product) : ProductOutputDto
    fun convertProductListToOutputDtoList(products: MutableList<Product>) : MutableList<ProductOutputDto>
}