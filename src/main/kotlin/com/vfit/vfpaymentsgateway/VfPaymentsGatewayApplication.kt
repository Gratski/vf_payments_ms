package com.vfit.vfpaymentsgateway

import com.vfit.vfpaymentsgateway.services.customers.CustomerService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class VfPaymentsGatewayApplication(val customerService: CustomerService){
/*
override fun run(vararg args: String?) {
   *
    val customer = Customer()
    customer.email = "dcsilvaWithNameAndMetadataAndShipping@mail.ru"
    customer.description = "description"
    customer.currency = "eur"
    customer.email = "factory@gmail.com"
    val address = Address()
    address.state = "Lisboa"
    address.city = "Lisboa"
    address.line1 = "this is line 1"
    address.line2 = "this is line 2"
    address.postalCode = "1150-299"
    address.country = "pt"
    customer.address = address

    //val createdCustomer = this.customerService.create(customer)
    val customerByEmail = this.customerService.findByEmail(customer.email)

    customerByEmail.name = "Mário Damião"
    val response = this.customerService.update(customerByEmail)



    println()


}
*/
}

fun main(args: Array<String>) {
runApplication<VfPaymentsGatewayApplication>(*args)
}
