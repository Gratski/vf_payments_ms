package com.vfit.vfpaymentsgateway.controllers

import com.vfit.vfpaymentsgateway.entities.dto.common.CustomResponseEntity
import com.vfit.vfpaymentsgateway.entities.dto.input.ProductInputDto
import com.vfit.vfpaymentsgateway.entities.dto.output.ProductOutputDto
import com.vfit.vfpaymentsgateway.services.products.ProductServiceInt
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.NotEmpty

@RestController
@RequestMapping("product")
class ProductController(private val productService: ProductServiceInt) {

    @PostMapping("/",
            consumes = [MediaType.APPLICATION_JSON_VALUE],
            produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@RequestBody productInputDto: ProductInputDto) : ProductOutputDto = this.productService.create(productInputDto)


    @PutMapping("/{productId}",
            consumes = [MediaType.APPLICATION_JSON_VALUE],
            produces = [MediaType.APPLICATION_JSON_VALUE])
    fun update(@RequestBody productInputDto: ProductInputDto,
               @NotEmpty(message = "Field is mandatory") @PathVariable("productId") productId: String) : ProductOutputDto
            = this.productService.update(productId, productInputDto)

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findById(@NotEmpty(message = "Field is mandatory") @PathVariable("id") id: String) : ProductOutputDto
            = this.productService.findById(id)


    @GetMapping("/all", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAllProducts() : CustomResponseEntity<ProductOutputDto>
            = this.productService.findAllProducts()

    @DeleteMapping("/{id}")
    fun delete(@NotEmpty(message = "Field is mandatory") @PathVariable("id") id: String)
            = this.productService.delete(id)
}