package com.vfit.vfpaymentsgateway.factories

interface FactoryInt<in T, out C, out U> {
    fun toCreate(t: T) : C
    fun toUpdate(t: T) : U
}