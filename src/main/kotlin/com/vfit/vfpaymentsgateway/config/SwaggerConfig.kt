package com.vfit.vfpaymentsgateway.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
open class SwaggerConfig {

    private companion object SwaggerApiConfig {
        fun buildConfig(): ApiInfo =
                ApiInfoBuilder()
                        .title("Test Cases Microservice")
                        .description("\"Spring Boot REST API for test cases microservice\"")
                        .version("1.0.0")
                        .license("BNP Paribas Cardif")
                        //.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                        .build()
    }

    @Bean
    open fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.vfit.vfpaymentsgateway.controllers"))
                .paths(PathSelectors.any())
                .build().apiInfo(SwaggerConfig.SwaggerApiConfig.buildConfig())
    }
}