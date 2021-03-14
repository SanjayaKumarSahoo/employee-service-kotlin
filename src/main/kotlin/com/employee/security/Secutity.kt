package com.employee.security

import com.employee.error.ErrorCodes
import com.employee.error.ErrorResponse
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class BasicAuth {
    var user: String = ""
    var password: String = ""
    var roles: List<String> = ArrayList()
        get() = field                     // getter
        set(value) { field = value }      // setter
}

class CustomFilter : GenericFilterBean() {
    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        chain.doFilter(request, response)
    }
}

class CustomBasicAuthenticationEntryPoint : BasicAuthenticationEntryPoint() {
    @Throws(IOException::class)
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        val cortixErrorResponse = ErrorResponse(ErrorCodes.UNAUTHORIZED)
        val writer = response.writer
        val responseResult = ObjectMapper().writeValueAsString(cortixErrorResponse)
        writer.println(responseResult)
    }

    override fun afterPropertiesSet() {
        realmName = "employee-service-kotlin"
        super.afterPropertiesSet()
    }
}