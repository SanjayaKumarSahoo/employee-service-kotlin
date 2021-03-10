package com.employee

import org.springframework.transaction.annotation.Transactional


interface IEmployeeService {
    fun createEmployee(employeeData: EmployeeData)
    fun getEmployeeById(id: Long): EmployeeData
}

open class EmployeeServiceImpl(private val employeeRepository: EmployeeRepository) : IEmployeeService {

    @Transactional
    override fun createEmployee(employeeData: EmployeeData) {
        val employee = convertEmployeeDataToEmployeeEntity(employeeData)
        employeeRepository.save(employee)
    }

    @Transactional(readOnly = true)
    override fun getEmployeeById(id: Long): EmployeeData {
        val employee = employeeRepository.getOne(id)
        val employeeData = convertEmployeeToEmployeeData(employee = employee)
        return employeeData
    }

    private fun convertEmployeeDataToEmployeeEntity(employeeData: EmployeeData): Employee {
        val department = Department(
            dName = employeeData.department.dName,
            loc = employeeData.department.loc
        )
        return Employee(
            ename = employeeData.ename,
            job = employeeData.job,
            mgr = employeeData.mgr,
            hireDate = employeeData.hireDate,
            sal = employeeData.sal,
            comm = employeeData.comm,
            department = department
        )
    }

    private fun convertEmployeeToEmployeeData(employee: Employee): EmployeeData {
        val departmentData = DepartmentData(
            dName = employee.department.dName,
            loc = employee.department.loc
        )

        return EmployeeData(
            ename = employee.ename,
            job = employee.job,
            mgr = employee.mgr,
            hireDate = employee.hireDate,
            sal = employee.sal,
            comm = employee.comm,
            department = departmentData
        )
    }
}