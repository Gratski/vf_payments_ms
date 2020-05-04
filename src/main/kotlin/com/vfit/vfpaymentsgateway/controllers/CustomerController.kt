package com.vfit.vfpaymentsgateway.controllers

import com.stripe.model.Customer
import com.vfit.vfpaymentsgateway.services.CustomerServiceInt
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.NotEmpty

@RestController
@RequestMapping("customer")
class CustomerController(val customerServiceInt: CustomerServiceInt) {

    @PostMapping("/",
            consumes = [MediaType.APPLICATION_JSON_VALUE],
            produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@RequestBody customer: Customer) : Customer = this.customerServiceInt.create(customer)

    @PutMapping("/{email}",
            consumes = [MediaType.APPLICATION_JSON_VALUE],
            produces = [MediaType.APPLICATION_JSON_VALUE])
    fun update(@RequestBody customer: Customer,
               @NotEmpty(message = "Field is mandatory") @PathVariable("email") email: String) : Customer
            = this.customerServiceInt.update(email, customer)

    @GetMapping("/{email}",
            produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findByEmail(@NotEmpty(message = "Field is mandatory") @PathVariable("email") email: String) : Customer
            = this.customerServiceInt.findByEmail(email)
}