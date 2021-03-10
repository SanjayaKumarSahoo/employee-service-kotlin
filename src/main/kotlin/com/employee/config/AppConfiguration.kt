package com.employee.config

import com.employee.EmployeeRepository
import com.employee.service.EmployeeServiceImpl
import com.employee.service.IEmployeeService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfiguration {

    @Bean
    fun employeeService(employeeRepository: EmployeeRepository): IEmployeeService =
            EmployeeServiceImpl(employeeRepository = employeeRepository)
}