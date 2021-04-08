package com.employee.config

import com.employee.props.ApplicationProperties
import com.employee.repository.DepartmentRepository
import com.employee.repository.EmployeeRepository
import com.employee.service.DepartmentServiceImpl
import com.employee.service.EmployeeServiceImpl
import com.employee.service.IDepartmentService
import com.employee.service.IEmployeeService
import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary


@Configuration
class ApplicationConfiguration {

    @Bean
    fun employeeService(employeeRepository: EmployeeRepository): IEmployeeService =
            EmployeeServiceImpl(employeeRepository = employeeRepository)


    @Bean
    fun departmentService(departmentRepository: DepartmentRepository): IDepartmentService =
            DepartmentServiceImpl(departmentRepository = departmentRepository)


    @Bean
    fun properties(): ApplicationProperties {
        return ApplicationProperties()
    }

    @Bean
    @Primary
    fun objectMapper(): ObjectMapper {
        return ObjectMapper()
                .registerKotlinModule()
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                .setVisibility(PropertyAccessor.CREATOR, JsonAutoDetect.Visibility.NONE)
                .setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE)
                .setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE)
                .setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.NONE)
    }
}