package com.employee.advice

import com.employee.error.ErrorCode
import com.employee.error.ErrorCodes
import com.employee.error.ErrorResponse
import com.employee.error.validation.FieldErrorDetail
import com.employee.exception.EmployeeServiceException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*
import java.util.stream.Collectors


@RestControllerAdvice
class EmployeeRestControllerAdvice @Autowired constructor(private val messageSource: MessageSource) :
    ResponseEntityExceptionHandler() {

    @ExceptionHandler(EmployeeServiceException::class)
    fun handleError(exception: EmployeeServiceException): ResponseEntity<ErrorResponse> {
        val errorcode: ErrorCode = exception.errorCode
        return ResponseEntity<ErrorResponse>(ErrorResponse(errorcode), HttpStatus.valueOf(errorcode.httpStatusCode))
    }

    @ExceptionHandler(Throwable::class)
    fun anyError(exception: Throwable?): ResponseEntity<ErrorResponse> {
        val errorCode: ErrorCode = ErrorCodes.INTERNAL_ERROR
        return ResponseEntity<ErrorResponse>(ErrorResponse(errorCode), HttpStatus.valueOf(errorCode.httpStatusCode))
    }

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders,
                                              status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val result = ex.bindingResult
        val fieldErrors = result.fieldErrors
        val fieldErrorDetails = processFieldErrors(fieldErrors)
        val errorMessages = fieldErrorDetails.stream().map(FieldErrorDetail::message).collect(Collectors.toList())
        val errorCode = ErrorCode(errorMessages, 400)
        return ResponseEntity<Any>(ErrorResponse(errorCode), HttpStatus.valueOf(errorCode.httpStatusCode))
    }

    private fun processFieldErrors(fieldErrors: List<FieldError>): List<FieldErrorDetail> {
        val errors: MutableList<FieldErrorDetail> = ArrayList()
        for (fieldError in fieldErrors) {
            val localizedErrorMessage = fieldError.field + " " + fieldError.defaultMessage
            errors.add(FieldErrorDetail(fieldError.field, localizedErrorMessage))
        }
        return errors
    }
}