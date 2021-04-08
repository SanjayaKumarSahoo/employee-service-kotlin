package com.employee.controller

import DepartmentData
import com.employee.error.ErrorMessages
import com.employee.exception.EmployeeServiceException
import com.employee.service.DepartmentId
import com.employee.service.IDepartmentService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/departments")
@Api(tags = ["Department API"], description = "Departments")

class DepartmentController(private val departmentService: IDepartmentService) {

    @ApiOperation(
        value = "Post department details",
        notes = "Post department details",
        code = 201,
        response = DepartmentId::class
    )
    @ApiResponses(
        ApiResponse(
            code = 500,
            message = ErrorMessages.INTERNAL_ERROR,
            response = EmployeeServiceException::class
        )
    )
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createDepartment(@RequestBody departmentData: DepartmentData): ResponseEntity<DepartmentId> {
        val departmentId = departmentService.createDepartment(departmentData = departmentData)
        return ResponseEntity.status(HttpStatus.OK).body(departmentId)

    }

    @ApiOperation(
        value = "Get department details by id",
        notes = "Get department details by id",
        code = 200,
        response = DepartmentData::class
    )
    @ApiResponses(
        ApiResponse(
            code = 500,
            message = ErrorMessages.INTERNAL_ERROR,
            response = EmployeeServiceException::class
        )
    )
    @GetMapping("/{id}")
    fun getEmployeeById(@PathVariable("id") id: Long): ResponseEntity<DepartmentData> {
        val departmentData = departmentService.getDepartmentById(id)
        return ResponseEntity.ok(departmentData)
    }
}
