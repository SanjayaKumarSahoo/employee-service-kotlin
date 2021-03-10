package com.employee.exception

import com.employee.error.ErrorCode
import com.employee.error.ErrorCodes

class EmployeeServiceException : RuntimeException {

    val errorCode: ErrorCode

    constructor(errorCode: ErrorCode, throwable: Throwable) : super(errorCode.messages.joinToString(","), throwable) {
        this.errorCode = errorCode
    }

    constructor(errorCode: ErrorCode) : super(errorCode.messages.joinToString(",")) {
        this.errorCode = errorCode;
    }

    fun wrap(ex: Throwable): EmployeeServiceException {
        return if (ex is EmployeeServiceException) ex else EmployeeServiceException(ErrorCodes.INTERNAL_ERROR)
    }
}