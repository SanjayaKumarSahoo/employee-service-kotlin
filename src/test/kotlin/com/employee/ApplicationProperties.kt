package com.employee

import com.employee.props.ApplicationProperties
import com.employee.security.BasicAuth
import org.assertj.core.api.Assertions
import org.assertj.core.groups.Tuple
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ApplicationPropertiesTest(@Autowired val properties: ApplicationProperties) {


    @Test
    fun testApplicationProperties() {
        val basicAuthList: List<BasicAuth> = properties.basicAuths
        Assertions.assertThat(basicAuthList)
            .hasSize(1)
            .extracting("user", "password")
            .contains(Tuple.tuple("allen", "password"))
    }
}