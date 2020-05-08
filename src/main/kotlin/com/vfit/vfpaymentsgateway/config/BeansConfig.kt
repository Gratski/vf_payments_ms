package com.vfit.vfpaymentsgateway.config

import com.vfit.vfpaymentsgateway.mapper.CustomerMapper
import com.vfit.vfpaymentsgateway.mapper.ProductMapper
import org.mapstruct.factory.Mappers
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class BeansConfig {

    @Bean
    open fun productMapper() : ProductMapper = Mappers.getMapper(ProductMapper::class.java)

    @Bean
    open fun customerMapper() : CustomerMapper = Mappers.getMapper(CustomerMapper::class.java)
}