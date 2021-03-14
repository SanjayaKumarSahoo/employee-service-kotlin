package com.employee.error

import java.util.Arrays

class ErrorCode(val messages: List<String>, val httpStatusCode: Int)

class ErrorResponse(errorCode: ErrorCode) {
    val messages = errorCode.messages
}

object ErrorMessages {
    const val INTERNAL_ERROR = "Internal server error occurred"
    const val UNAUTHORIZED = "Unauthorized"
    const val EMPLOYEE_NOT_FOUND = "Employee not found"
}

object ErrorCodes {
    val INTERNAL_ERROR = ErrorCode(Arrays.asList(ErrorMessages.INTERNAL_ERROR), 500);
    val UNAUTHORIZED = ErrorCode(Arrays.asList(ErrorMessages.UNAUTHORIZED), 401);
    val EMPLOYEE_NOT_FOUND = ErrorCode(Arrays.asList(ErrorMessages.EMPLOYEE_NOT_FOUND), 400)
}