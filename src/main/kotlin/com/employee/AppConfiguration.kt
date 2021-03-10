package com.employee

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfiguration {

    @Bean
    fun employeeService(employeeRepository: EmployeeRepository): IEmployeeService =
        EmployeeServiceImpl(employeeRepository = employeeRepository)
}