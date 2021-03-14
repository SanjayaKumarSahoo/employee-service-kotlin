package com.employee.config

import com.employee.props.ApplicationProperties
import com.employee.security.CustomBasicAuthenticationEntryPoint
import com.employee.security.CustomFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Configuration
@EnableWebSecurity
class HttpBasicCustomWebSecurityConfigurerAdapter : WebSecurityConfigurerAdapter() {


    @Autowired
    val properties: ApplicationProperties? = null

    @Autowired
    val customBasicAuthenticationEntryPoint: CustomBasicAuthenticationEntryPoint? = null

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        var userDetailsManagerConfigurer = auth.inMemoryAuthentication()
        for (basicAuth in properties?.basicAuths!!) {
            userDetailsManagerConfigurer = userDetailsManagerConfigurer
                .withUser(basicAuth.user)
                .password(passwordEncoder().encode(basicAuth.password))
                .authorities(*basicAuth.roles.toTypedArray())
                .roles(*basicAuth.roles.toTypedArray())
                .and()
        }
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http // csrf disable, since REST is immune to csrf attacks
            .csrf()
            .disable()
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .httpBasic()
            .authenticationEntryPoint(customBasicAuthenticationEntryPoint)
        http.addFilterAfter(CustomFilter(), BasicAuthenticationFilter::class.java)
    }

    @Throws(Exception::class)
    override fun configure(web: WebSecurity) {
        super.configure(web)
        web.ignoring()
            .antMatchers("/**/interface", "/**/health")
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun customBasicAuthenticationEntryPoint(): CustomBasicAuthenticationEntryPoint {
        return CustomBasicAuthenticationEntryPoint()
    }
}