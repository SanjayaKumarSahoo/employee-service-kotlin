package com.employee.controller

import EmployeeData
import com.employee.service.IEmployeeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/employees")
class EmployeeController(private val employeeService: IEmployeeService) {

    @PostMapping
    fun createEmployee(employeeData: EmployeeData): ResponseEntity<Any> {
        employeeService.createEmployee(employeeData)
        return ResponseEntity.accepted().build()
    }

    @GetMapping("/{id}")
    fun getEmployeeById(@PathVariable("id") id: Long): ResponseEntity<EmployeeData> {
        val employeeData = employeeService.getEmployeeById(id)
        return ResponseEntity.ok(employeeData)
    }
}