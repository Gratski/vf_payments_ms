package com.vfit.vfpaymentsgateway.entities.dto.input

class ProductInputDto {
    var id: String? = null
    var name: String? = null
    var active: Boolean? = null
    var attributes: List<String>? = null
    //var caption: String? = null
    var created: Long? = null
    var deactivateOn: List<String>? = null
    var deleted: Boolean? = null
    var description: String? = null
    var images: List<String>? = null
    var livemode: Boolean? = null
    var metadata: Map<String, String>? = null
    //var shippable: Boolean? = null
    var statementDescriptor: String? = null
    var type: String? = null
    var unitLabel: String? = null
}