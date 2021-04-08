package com.employee.service

import DepartmentData
import EmployeeData
import EmployeeId
import com.employee.error.ErrorCodes
import com.employee.exception.EmployeeServiceException
import com.employee.repository.Department
import com.employee.repository.Employee
import com.employee.repository.EmployeeRepository
import org.springframework.transaction.annotation.Transactional
import java.text.SimpleDateFormat
import java.util.Date
import javax.persistence.EntityNotFoundException




interface IEmployeeService {
    fun createEmployee(employeeData: EmployeeData): EmployeeId?
    fun getEmployeeById(id: Long): EmployeeData
}

open class EmployeeServiceImpl(private val employeeRepository: EmployeeRepository) : IEmployeeService {

    @Transactional
    override fun createEmployee(employeeData: EmployeeData): EmployeeId? {
        val employee = convertEmployeeDataToEmployeeEntity(employeeData)
        return employeeRepository.save(employee).empno?.let { EmployeeId(employeeId = it) }
    }

    @Transactional(readOnly = true)
    override fun getEmployeeById(id: Long): EmployeeData {
        try {
            val employee = employeeRepository.getOne(id)
            return convertEmployeeToEmployeeData(employee = employee)
        } catch (e: EntityNotFoundException) {
            throw EmployeeServiceException(ErrorCodes.EMPLOYEE_NOT_FOUND)
        }
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
            hireDate = parseDate(employeeData.hireDate),
            sal = employeeData.sal,
            comm = employeeData.comm,
            department = department
        )
    }

    private fun parseDate(date: String): Date {
        val formatter = SimpleDateFormat("dd-MM-yyyy")
        return formatter.parse(date);
    }

    private fun formatDate(date: Date): String {
        val formatter = SimpleDateFormat("dd-MM-yyyy")
        return formatter.format(date);
    }

    private fun convertEmployeeToEmployeeData(employee: Employee): EmployeeData {
        val departmentData = DepartmentData(
            dName = employee.department.dName,
            loc = employee.department.loc,
            deptNo = employee.department.deptNo
        )

        return EmployeeData(
            ename = employee.ename,
            job = employee.job,
            mgr = employee.mgr,
            hireDate = formatDate(employee.hireDate),
            sal = employee.sal,
            comm = employee.comm,
            department = departmentData
        )
    }
}