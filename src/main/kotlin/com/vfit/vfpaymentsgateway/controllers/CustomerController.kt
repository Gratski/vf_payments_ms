package com.vfit.vfpaymentsgateway.controllers

import com.vfit.vfpaymentsgateway.entities.dto.input.CustomerInputDto
import com.vfit.vfpaymentsgateway.entities.dto.output.CustomerOutputDto
import com.vfit.vfpaymentsgateway.services.customers.CustomerServiceInt
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.NotEmpty

@RestController
@RequestMapping("customer")
class CustomerController(val customerServiceInt: CustomerServiceInt) {

    @PostMapping("/",
            consumes = [MediaType.APPLICATION_JSON_VALUE],
            produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@RequestBody customer: CustomerInputDto) : CustomerOutputDto = this.customerServiceInt.create(customer)

    @PutMapping("/{email}",
            consumes = [MediaType.APPLICATION_JSON_VALUE],
            produces = [MediaType.APPLICATION_JSON_VALUE])
    fun update(@RequestBody customer: CustomerInputDto,
               @NotEmpty(message = "Field is mandatory") @PathVariable("email") email: String) : CustomerOutputDto
            = this.customerServiceInt.update(email, customer)

    @GetMapping("/{email}",
            produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findByEmail(@NotEmpty(message = "Field is mandatory") @PathVariable("email") email: String) : CustomerOutputDto
            = this.customerServiceInt.findByEmail(email)
}