package com.employee.controller

import DepartmentData
import DepartmentId
import com.employee.error.ErrorMessages
import com.employee.exception.EmployeeServiceException
import com.employee.service.IDepartmentService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/departments")
@Api(tags = ["Department API"], description = "Departments")
@Validated
class DepartmentController(private val departmentService: IDepartmentService) {

    var logger: Logger = LoggerFactory.getLogger(DepartmentController::class.java)

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
    fun createDepartment(@RequestBody @Valid departmentData: DepartmentData): ResponseEntity<DepartmentId> {
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
    fun getEmployeeById(@PathVariable("id", required = true) id: Long): ResponseEntity<DepartmentData> {
        val departmentData = departmentService.getDepartmentById(id)
        return ResponseEntity.ok(departmentData)
    }
}
