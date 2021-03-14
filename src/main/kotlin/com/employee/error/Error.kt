package com.employee.error

import java.util.Arrays

class ErrorCode(val messages: List<String>, val httpStatusCode: Int)

class ErrorResponse(errorCode: ErrorCode) {
    val messages = errorCode.messages
}

object ErrorMessages {
    const val INTERNAL_ERROR = "Internal server error occurred"
    const val UNAUTHORIZED = "Unauthorized"
}

object ErrorCodes {
    val INTERNAL_ERROR = ErrorCode(Arrays.asList(ErrorMessages.INTERNAL_ERROR), 500);
    val UNAUTHORIZED = ErrorCode(Arrays.asList(ErrorMessages.UNAUTHORIZED), 401);

}