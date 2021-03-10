package com.employee

import com.employee.repository.Department
import com.employee.repository.Employee
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.annotation.DirtiesContext
import java.util.*

@DataJpaTest
@DirtiesContext
class EmployeeRepositoryTest @Autowired constructor(var employeeRepository: EmployeeRepository) {

    @Test
    fun `save employee and department details`() {

        val department = Department(
            dName = "SALES",
            loc = "NEWYORK"
        )
        val allen = Employee(
            ename = "ALLEN",
            job = "MANAGER",
            mgr = 2,
            hireDate = Date(),
            sal = 100.0,
            comm = 10.0,
            department = department
        )
        val savedAllen = employeeRepository.save(allen)
        Assertions.assertThat(savedAllen)
            .extracting("ename", "job", "mgr", "sal", "comm", "empno")
            .contains("ALLEN", "MANAGER", 2, 100.0, 10.0, 1L)
        Assertions.assertThat(savedAllen.hireDate)
            .isBeforeOrEqualTo(Date())
        Assertions.assertThat(savedAllen.department)
            .extracting("dName", "loc")
            .contains("SALES", "NEWYORK")
    }
}