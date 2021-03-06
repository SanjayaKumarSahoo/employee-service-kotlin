package com.employee.controller

import EmployeeData
import EmployeeId
import com.employee.error.ErrorMessages
import com.employee.exception.EmployeeServiceException
import com.employee.service.IEmployeeService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/employees")
@Api(tags = ["Employees API"], description = "Employees")
class EmployeeController(private val employeeService: IEmployeeService) {

    var logger: Logger = LoggerFactory.getLogger(EmployeeController::class.java)

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
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createEmployee(@RequestBody employee: EmployeeData): ResponseEntity<EmployeeId> {
        val employeeId = employeeService.createEmployee(employee)
        return ResponseEntity.status(HttpStatus.OK).body(employeeId)
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