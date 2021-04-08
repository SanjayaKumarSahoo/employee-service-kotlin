package com.employee

import com.employee.repository.Department
import com.employee.repository.DepartmentRepository
import com.employee.repository.Employee
import com.employee.repository.EmployeeRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.annotation.DirtiesContext
import java.util.Date

@DataJpaTest
@DirtiesContext
class DepartmentRepositoryTest @Autowired constructor(
    var departmentRepository: DepartmentRepository
) {

    @Test
    fun `save department details`() {

        val department = Department(
            dName = "SALES",
            loc = "NEWYORK"
        )
        val savedDepartment = departmentRepository.save(department)
        Assertions.assertThat(savedDepartment)
            .extracting("dName", "loc", "deptNo")
            .contains("SALES", "NEWYORK", 1L)
        Assertions.assertThat(savedDepartment.employees).hasSize(0);
    }
}

@DataJpaTest
@DirtiesContext
class EmployeeRepositoryTest @Autowired constructor(
    var employeeRepository: EmployeeRepository,
    var departmentRepository: DepartmentRepository
) {

    @Test
    fun `save employee to department`() {

        val salesDepartment = Department(
            dName = "SALES",
            loc = "NEWYORK"
        )
        var savedDepartment = departmentRepository.save(salesDepartment)

        val allen = Employee(
            ename = "ALLEN",
            job = "MANAGER",
            mgr = 2,
            hireDate = Date(),
            sal = 100.0,
            comm = 10.0,
            department = savedDepartment
        )

        val savedAllen = employeeRepository.save(allen);
        Assertions.assertThat(savedAllen.empno).isNotNull()
        Assertions.assertThat(savedAllen)
            .extracting("ename", "job", "mgr", "sal", "comm")
            .contains("ALLEN", "MANAGER", 2, 100.0, 10.0)
        Assertions.assertThat(savedAllen.hireDate)
            .isBeforeOrEqualTo(Date())

        Assertions.assertThat(savedAllen.department.deptNo).isNotNull()
        Assertions.assertThat(savedAllen.department)
            .extracting("dName", "loc")
            .contains("SALES", "NEWYORK")
    }
}