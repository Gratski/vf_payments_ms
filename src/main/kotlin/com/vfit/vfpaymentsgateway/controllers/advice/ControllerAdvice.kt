package com.vfit.vfpaymentsgateway.controllers.advice

import com.vfit.vfpaymentsgateway.exceptions.EmailExistsException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.client.HttpClientErrorException.BadRequest
import org.springframework.web.client.HttpClientErrorException.Gone
import org.springframework.web.client.HttpClientErrorException.Forbidden
import org.springframework.web.client.HttpClientErrorException.Conflict
import org.springframework.web.client.HttpClientErrorException.TooManyRequests
import org.springframework.web.client.HttpClientErrorException.UnprocessableEntity
import org.springframework.web.client.HttpClientErrorException.UnsupportedMediaType
import org.springframework.web.client.HttpClientErrorException.NotFound
import org.springframework.web.client.HttpClientErrorException.MethodNotAllowed
import org.springframework.web.client.HttpClientErrorException.NotAcceptable
import org.springframework.web.client.HttpClientErrorException.Unauthorized
import org.springframework.web.client.HttpServerErrorException.InternalServerError
import org.springframework.web.client.HttpServerErrorException.GatewayTimeout
import org.springframework.web.client.HttpServerErrorException.NotImplemented

@ControllerAdvice
class ControllerAdvice {
    @ExceptionHandler(BadRequest::class, Gone::class,
            Forbidden::class, Conflict::class,
            TooManyRequests::class, UnprocessableEntity::class,
            UnsupportedMediaType::class, NotFound::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleBadRequestException() {}

    @ExceptionHandler(InternalServerError::class, GatewayTimeout::class,
            MethodNotAllowed::class, NotAcceptable::class,
            NotImplemented::class, Unauthorized::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleInternalServerErrorException() {
    }

    @ExceptionHandler(EmailExistsException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleEmailExistsException(){
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): Error? {
        val result = ex.bindingResult
        val fieldErrors = result.fieldErrors
        return processFieldErrors(fieldErrors)
    }

    private fun processFieldErrors(fieldErrors: List<org.springframework.validation.FieldError>): Error? {
        val error = Error(HttpStatus.BAD_REQUEST.value(), "Validation error")
        for (fieldError in fieldErrors) {
            error.addFieldError(fieldError.field, fieldError.defaultMessage)
        }
        return error
    }
}

class Error(val status: Int, val message: String) {
    private val fieldFieldErrors: MutableList<FieldError> = ArrayList()

    fun addFieldError(field: String?, message: String?) {
        val error = FieldError(field, message)
        fieldFieldErrors.add(error)
    }

    fun getFieldErrors(): List<FieldError> {
        return fieldFieldErrors
    }
}

class FieldError(val fieldName: String?, val message: String?)