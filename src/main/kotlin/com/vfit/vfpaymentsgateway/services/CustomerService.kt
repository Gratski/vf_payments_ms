package com.vfit.vfpaymentsgateway.services

import com.stripe.model.Customer
import com.stripe.param.CustomerCreateParams
import com.stripe.param.CustomerListParams
import com.stripe.param.CustomerUpdateParams
import com.vfit.vfpaymentsgateway.entities.dto.input.CustomerInputDto
import com.vfit.vfpaymentsgateway.entities.dto.output.CustomResponseEntity
import com.vfit.vfpaymentsgateway.entities.dto.output.CustomerOutputDto
import com.vfit.vfpaymentsgateway.exceptions.EmailExistsException
import com.vfit.vfpaymentsgateway.factories.FactoryInf
import com.vfit.vfpaymentsgateway.mapper.CustomerConverter
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerService(val factory : FactoryInf<CustomerInputDto, CustomerCreateParams, CustomerUpdateParams>,
                      val converter : CustomerConverter) : StripeSetup(), CustomerServiceInt {

    override fun create(customerInputDto: CustomerInputDto): CustomerOutputDto {
        val customerCreateParams = factory.toCreate(customerInputDto)
        return converter.convertToDto(Customer.create(customerCreateParams))
    }

    @Deprecated("Just to see how call an object properties")
    override fun findByName(name: String): CustomResponseEntity<CustomerOutputDto> {
        val params: MutableMap<String, Any> = HashMap()
        params["name"] = name

        val account: MutableList<Customer> = Customer.list(params).data
        val dto = converter.convertToDto(account)

        return CustomResponseEntity(dto, dto.size)
    }

    override fun findByEmail(email: String): CustomerOutputDto {
        var query = CustomerListParams.builder().setEmail(email).build()
        val result: MutableList<Customer>? = Customer.list(query).data
        lateinit var customer: Customer
        if(!result.isNullOrEmpty()){
            if(result.size > 1){
                throw EmailExistsException(email)
            }
            customer = result.first()
        }
        return converter.convertToDto(customer)
    }

    override fun update(email: String, customerInputDto: CustomerInputDto): CustomerOutputDto {
        val customerByEmailResult= this.findByEmail(email)
        val actualCustomer = Customer.retrieve(customerByEmailResult.id)
        val customerUpdateParams = this.factory.toUpdate(customerInputDto)
        val response = actualCustomer.update(customerUpdateParams)
        return converter.convertToDto(response)
    }
}