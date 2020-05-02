package com.vfit.vfpaymentsgateway

import com.vfit.vfpaymentsgateway.services.CustomerService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class VfPaymentsGatewayApplicationTests {

	@Autowired
	val customerService = CustomerService()

	//@Test
	fun contextLoads() {
	}

}
