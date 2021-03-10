package com.employee

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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