package com.vfit.vfpaymentsgateway.entities.dto

import java.io.Serializable

class AddressDTO(var city: String? = null, var country: String? = null,
                 var line1: String? = null, var line2: String? = null,
                 var postalCode: String? = null, var state: String? = null) : Serializable