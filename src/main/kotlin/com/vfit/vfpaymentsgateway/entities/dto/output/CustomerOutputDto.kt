package com.vfit.vfpaymentsgateway.entities.dto.output

import com.vfit.vfpaymentsgateway.entities.dto.common.AddressDTO

class CustomerOutputDto(var address: AddressDTO? = null,
                        var balance: Long? = null,
                        var currency: String? = null,
                        var deleted: Boolean? = false,
                        var delinquent: Boolean? = false,
                        var description: String? = null,
                        var email: String? = null,
                        var id: String? = null,
                        var invoicePrefix: String? = null,
                        var livemode: Boolean? = false,
                        var metadata: Map<String, String>? = null,
                        var name: String? = null,
                        var phone: String? = null,
                        var preferredLocales: List<String>? = null,
                        var taxExempt: String? = null)