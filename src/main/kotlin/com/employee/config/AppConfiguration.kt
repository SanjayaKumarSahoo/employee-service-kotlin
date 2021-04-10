package com.employee.config

import com.employee.Constants
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
import org.apache.catalina.connector.Connector
import org.apache.coyote.http2.Http2Protocol
import org.jasypt.encryption.StringEncryptor
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean


@EnableTransactionManagement
@EnableJpaRepositories(basePackages = ["com.employee.repository"])
@Configuration
class ApplicationConfiguration {

    @Bean("jasyptStringEncryptor")
    fun stringEncryptor(): StringEncryptor? {
        val encryptor = StandardPBEStringEncryptor()
        encryptor.setPassword(System.getenv(Constants.ENV_PASSWORD))
        return encryptor
    }

    @Bean
    fun messageSource(): MessageSource? {
        val messageSource = ReloadableResourceBundleMessageSource()
        messageSource.setBasename("classpath:messages")
        messageSource.setDefaultEncoding("UTF-8")
        return messageSource
    }

    @Bean
    fun getValidator(messageSource: MessageSource?): LocalValidatorFactoryBean? {
        val bean = LocalValidatorFactoryBean()
        bean.setValidationMessageSource(messageSource!!)
        return bean
    }

    @Bean
    fun employeeService(
            employeeRepository: EmployeeRepository,
            departmentRepository: DepartmentRepository
    ): IEmployeeService =
            EmployeeServiceImpl(employeeRepository = employeeRepository, departmentRepository)


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

    // required for setting of http2 protocol in tomcat
    @Bean
    fun connectorCustomizer(): TomcatConnectorCustomizer? {
        return TomcatConnectorCustomizer { connector: Connector -> connector.addUpgradeProtocol(Http2Protocol()) }
    }
}

/*fun main(args: Array<String>) {
    val encryptor = StandardPBEStringEncryptor()
    encryptor.setPassword("password")
    println(encryptor.encrypt("password"))
}*/
