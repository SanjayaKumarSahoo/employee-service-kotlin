package com.employee.service

import EmployeeData
import EmployeeId
import com.employee.error.ErrorCodes
import com.employee.exception.EmployeeServiceException
import com.employee.repository.Department
import com.employee.repository.DepartmentRepository
import com.employee.repository.Employee
import com.employee.repository.EmployeeRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.transaction.annotation.Transactional
import java.text.SimpleDateFormat
import java.util.*


interface IEmployeeService {
    fun createEmployee(employeeData: EmployeeData): EmployeeId?
    fun getEmployeeById(id: Long): EmployeeData?
}

open class EmployeeServiceImpl(private val employeeRepository: EmployeeRepository,
                               private val departmentRepository: DepartmentRepository) : IEmployeeService {

    var logger: Logger = LoggerFactory.getLogger(EmployeeServiceImpl::class.java)

    @Transactional
    override fun createEmployee(employeeData: EmployeeData): EmployeeId? {
        try {
            val department = departmentRepository.getOne(employeeData.deptNo)
            val savedEmployee = employeeRepository.save(convertEmployeeDataToEmployeeEntity(employeeData, department))
            department.employees.add(savedEmployee)
            return savedEmployee.empno?.let { EmployeeId(it) }
        } catch (e: Exception) {
            logger.error("Error occurred", e)
            throw EmployeeServiceException(ErrorCodes.INTERNAL_ERROR)
        }
    }

    @Transactional(readOnly = true)
    override fun getEmployeeById(id: Long): EmployeeData? {
        try {
            val employee = employeeRepository.getOne(id)
            return convertEmployeeToEmployeeData(employee = employee)
        } catch (e: JpaObjectRetrievalFailureException) {
            logger.error("Error occurred", e)
            throw EmployeeServiceException(ErrorCodes.EMPLOYEE_NOT_FOUND)
        }
    }

    private fun convertEmployeeDataToEmployeeEntity(employeeData: EmployeeData, department: Department): Employee {
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

    private fun convertEmployeeToEmployeeData(employee: Employee): EmployeeData? {
        val deptNo = employee.department.deptNo
        return deptNo?.let {
            employee.empno?.let { it1 ->
                EmployeeData(
                        ename = employee.ename,
                        job = employee.job,
                        mgr = employee.mgr,
                        hireDate = formatDate(employee.hireDate),
                        sal = employee.sal,
                        comm = employee.comm,
                        deptNo = it,
                        empNo = it1
                )
            }
        }
    }
}