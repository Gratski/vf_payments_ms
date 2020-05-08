package com.vfit.vfpaymentsgateway.services.products

import com.stripe.model.Product
import com.stripe.param.ProductCreateParams
import com.stripe.param.ProductListParams
import com.vfit.vfpaymentsgateway.entities.dto.common.CustomResponseEntity
import com.vfit.vfpaymentsgateway.entities.dto.input.ProductInputDto
import com.vfit.vfpaymentsgateway.entities.dto.output.ProductOutputDto
import com.vfit.vfpaymentsgateway.exceptions.FactoryExceptionMessages
import com.vfit.vfpaymentsgateway.exceptions.NoMatchPrimaryKeyException
import com.vfit.vfpaymentsgateway.factories.ProductFactory
import com.vfit.vfpaymentsgateway.mapper.ProductMapper
import com.vfit.vfpaymentsgateway.services.StripeSetup
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductService(private val factory: ProductFactory, private val productMapper: ProductMapper) : StripeSetup(), ProductServiceInt {
    override fun create(productInputDto: ProductInputDto): ProductOutputDto {
        productInputDto.type = ProductCreateParams.Type.SERVICE.name
        val productCreateParams = productMapper.convertInputDtoToProductCreateParams(productInputDto)
        val product = Product.create(productCreateParams)
        return productMapper.convertProductToOutputDTO(product)
    }

    override fun update(productId: String, productInputDto: ProductInputDto): ProductOutputDto {
        if(!Objects.equals(productId, productInputDto.id)){
            throw NoMatchPrimaryKeyException(FactoryExceptionMessages().noMatchPrimaryKeys(productId, productInputDto.id.toString()) )
        }
        var product = Product.retrieve(productInputDto.id)
        var productUpdateParams = factory.toUpdate(productInputDto)
        val response = product.update(productUpdateParams)
        return productMapper.convertProductToOutputDTO(response)
        
    }

    override fun findById(productId: String): ProductOutputDto {
        val product = Product.retrieve(productId)
        return productMapper.convertProductToOutputDTO(product)
    }

    override fun findAllProducts(): CustomResponseEntity<ProductOutputDto> {
        val query = ProductListParams.builder().setLimit(1000).build()
        val result: MutableList<Product> = Product.list(query).data
        val dto = productMapper.convertProductListToOutputDtoList(result)
        return CustomResponseEntity(dto, dto.size)
    }

    override fun delete(productId: String) {
        val productToDelete = Product.retrieve(productId)
        productToDelete.delete()
    }
}