package com.vfit.vfpaymentsgateway.factories

import com.stripe.param.ProductCreateParams
import com.stripe.param.ProductUpdateParams
import com.vfit.vfpaymentsgateway.config.VFitConfig
import com.vfit.vfpaymentsgateway.entities.dto.input.ProductInputDto
import org.springframework.stereotype.Component

@Component
class ProductFactory(val vFitConfig: VFitConfig) : FactoryInt<ProductInputDto, ProductCreateParams, ProductUpdateParams> {

    override fun toCreate(productInputDto: ProductInputDto): ProductCreateParams {
        return ProductCreateParams.builder()
                .setName(productInputDto.name)
                .addAllAttribute(productInputDto.attributes)
                .addAllDeactivateOn(productInputDto.deactivateOn)
                .addAllImage(productInputDto.images)
                .setActive(productInputDto.active)
                .setDescription(productInputDto.description)
                .setUnitLabel(productInputDto.unitLabel)
                .setType(ProductCreateParams.Type.SERVICE)
                .build()
    }

    override fun toUpdate(productInputDto: ProductInputDto): ProductUpdateParams {
        return ProductUpdateParams.builder()
                .setName(productInputDto.name)
                .addAllAttribute(productInputDto.attributes)
                .addAllDeactivateOn(productInputDto.deactivateOn)
                .addAllImage(productInputDto.images)
                .setActive(productInputDto.active)
                .setDescription(productInputDto.description)
                .setUnitLabel(productInputDto.unitLabel)
                .build()
    }

}