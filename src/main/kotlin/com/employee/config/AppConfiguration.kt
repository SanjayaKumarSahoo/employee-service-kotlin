package com.employee.config

import com.employee.props.ApplicationProperties
import com.employee.repository.DepartmentRepository
import com.employee.repository.EmployeeRepository
import com.employee.service.DepartmentServiceImpl
import com.employee.service.EmployeeServiceImpl
import com.employee.service.IDepartmentService
import com.employee.service.IEmployeeService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


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

    /*@Bean
    @Primary
    fun objectMapper(): ObjectMapper {
        return ObjectMapper()
            .registerModule(KotlinModule())
            .registerModule(JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
    }*/


    /*@Bean
    fun connectorCustomizer(): TomcatConnectorCustomizer? {
        return TomcatConnectorCustomizer { connector: Connector ->
            connector.addUpgradeProtocol(
                Http2Protocol()
            )
        }
    }*/
}