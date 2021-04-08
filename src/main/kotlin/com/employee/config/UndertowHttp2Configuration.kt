package com.employee.config

/*import io.undertow.Undertow
import io.undertow.UndertowOptions.ENABLE_HTTP2
import org.springframework.boot.autoconfigure.AutoConfigureBefore
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration
import org.springframework.boot.web.embedded.undertow.UndertowBuilderCustomizer
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration*/


//@Configuration
//@ConditionalOnClass(Undertow::class)
//@AutoConfigureBefore(ServletWebServerFactoryAutoConfiguration::class)
class UndertowHttp2Configuration {

    /*@Bean
    fun servletWebServerFactory(): ServletWebServerFactory? {
        val factory = UndertowServletWebServerFactory()
        factory.addBuilderCustomizers(UndertowBuilderCustomizer { builder: Undertow.Builder -> builder.setServerOption(ENABLE_HTTP2, true) })
        return factory
    }*/
}