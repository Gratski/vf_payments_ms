package com.vfit.vfpaymentsgateway.exceptions

class FactoryExceptionMessages {

    fun noMatchPrimaryKeys(argId: String, objectId : String) =
            "Primary key don't match: argId: $argId, objectId: $objectId"
}