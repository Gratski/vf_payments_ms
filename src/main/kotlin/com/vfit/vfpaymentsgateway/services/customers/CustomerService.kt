package com.vfit.vfpaymentsgateway.services.customers

import com.stripe.model.Customer
import com.stripe.param.CustomerCreateParams
import com.stripe.param.CustomerListParams
import com.stripe.param.CustomerUpdateParams
import com.vfit.vfpaymentsgateway.entities.dto.input.CustomerInputDto
import com.vfit.vfpaymentsgateway.entities.dto.common.CustomResponseEntity
import com.vfit.vfpaymentsgateway.entities.dto.output.CustomerOutputDto
import com.vfit.vfpaymentsgateway.exceptions.EmailExistsException
import com.vfit.vfpaymentsgateway.factories.FactoryInt
import com.vfit.vfpaymentsgateway.mapper.CustomerMapper
import com.vfit.vfpaymentsgateway.services.StripeSetup
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerService(private val factory : FactoryInt<CustomerInputDto, CustomerCreateParams, CustomerUpdateParams>,
                      private val mapper : CustomerMapper) : StripeSetup(), CustomerServiceInt {

    override fun create(customerInputDto: CustomerInputDto): CustomerOutputDto {
        val customerCreateParams = factory.toCreate(customerInputDto)
        //TODO: investigar pq estou a ter problemas com o mapper a baixo
        //val customerCreateParamsTest = mapper.convertInputDtoToCreateParams(customerInputDto)
        return mapper.convertEntityToOutputDTO(Customer.create(customerCreateParams))
    }

    @Deprecated("Just to see how call an object properties")
    override fun findByName(name: String): CustomResponseEntity<CustomerOutputDto> {
        val params: MutableMap<String, Any> = HashMap()
        params["name"] = name

        val account: MutableList<Customer> = Customer.list(params).data
        val dto = mapper.convertEntityListToOutputDtoList(account)

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
        return mapper.convertEntityToOutputDTO(customer)
    }

    override fun update(email: String, customerInputDto: CustomerInputDto): CustomerOutputDto {
        val customerByEmailResult= this.findByEmail(email)
        val actualCustomer = Customer.retrieve(customerByEmailResult.id)
        val customerUpdateParams = this.factory.toUpdate(customerInputDto)
        val response = actualCustomer.update(customerUpdateParams)
        return mapper.convertEntityToOutputDTO(response)
    }
}