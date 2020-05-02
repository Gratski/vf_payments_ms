package com.vfit.vfpaymentsgateway

import com.vfit.vfpaymentsgateway.services.CustomerService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VfPaymentsGatewayApplication(val customerService: CustomerService): CommandLineRunner{
	override fun run(vararg args: String?) {
		val customerByEmail = this.customerService.findByEmail("dcsilva2@mail.ru")

		println()
	}

}

fun main(args: Array<String>) {
	runApplication<VfPaymentsGatewayApplication>(*args)
}
