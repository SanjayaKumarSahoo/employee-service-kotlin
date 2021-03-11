package com.employee.controller

import EmployeeData
import com.employee.error.ErrorMessages
import com.employee.exception.EmployeeServiceException
import com.employee.service.IEmployeeService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/employees")
@Api(tags = ["Employees API"], description = "Employees")
class EmployeeController(private val employeeService: IEmployeeService) {

    @ApiOperation(
        value = "Post employee details",
        notes = "Post employee details",
        code = 201,
        response = String::class
    )
    @ApiResponses(
        ApiResponse(
            code = 500,
            message = ErrorMessages.INTERNAL_ERROR,
            response = EmployeeServiceException::class
        )
    )
    @PostMapping
    fun createEmployee(employeeData: EmployeeData): ResponseEntity<Any> {
        employeeService.createEmployee(employeeData)
        return ResponseEntity.status(201).build()

    }

    @ApiOperation(
        value = "Get employee details by id",
        notes = "Get employee details by id",
        code = 200,
        response = EmployeeData::class
    )
    @ApiResponses(
        ApiResponse(
            code = 500,
            message = ErrorMessages.INTERNAL_ERROR,
            response = EmployeeServiceException::class
        )
    )
    @GetMapping("/{id}")
    fun getEmployeeById(@PathVariable("id") id: Long): ResponseEntity<EmployeeData> {
        val employeeData = employeeService.getEmployeeById(id)
        return ResponseEntity.ok(employeeData)
    }
}