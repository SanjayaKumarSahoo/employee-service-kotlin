package com.employee.props

import com.employee.security.BasicAuth
import org.springframework.boot.context.properties.ConfigurationProperties
import java.util.ArrayList

@ConfigurationProperties(prefix = "")
class ApplicationProperties {
    val basicAuths: List<BasicAuth> = ArrayList()
}