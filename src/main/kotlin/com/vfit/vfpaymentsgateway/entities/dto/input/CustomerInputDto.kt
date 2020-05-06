package com.vfit.vfpaymentsgateway.entities.dto.input

import com.vfit.vfpaymentsgateway.entities.dto.common.AddressDTO

class CustomerInputDto(var id: String? = null,
                       var currency: String? = null,
                       var description: String? = null,
                       var email: String? = null,
                       var invoicePrefix: String? = null,
                       var metadata: Map<String, String>? = null,
                       var name: String? = null,
                       var phone: String? = null,
                       var address: AddressDTO? = null)