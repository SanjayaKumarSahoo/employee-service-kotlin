package com.employee.service

import DepartmentData
import DepartmentId
import com.employee.error.ErrorCodes
import com.employee.exception.EmployeeServiceException
import com.employee.repository.Department
import com.employee.repository.DepartmentRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.transaction.annotation.Transactional


interface IDepartmentService {
    fun createDepartment(departmentData: DepartmentData): DepartmentId?

    fun getDepartmentById(id: Long): DepartmentData
}


open class DepartmentServiceImpl(private val departmentRepository: DepartmentRepository) : IDepartmentService {

    var logger: Logger = LoggerFactory.getLogger(DepartmentServiceImpl::class.java)

    @Transactional
    override fun createDepartment(departmentData: DepartmentData): DepartmentId? {
        try {
            val department = convertDepartmentDataToDepartmentEntity(departmentData)
            return departmentRepository.save(department).deptNo?.let { DepartmentId(deptNo = it) };
        } catch (e: Exception) {
            logger.error("Error occurred", e)
            throw EmployeeServiceException(ErrorCodes.INTERNAL_ERROR)
        }
    }

    @Transactional(readOnly = true)
    override fun getDepartmentById(id: Long): DepartmentData {
        try {
            val department = departmentRepository.getOne(id)
            return convertDepartmentToDepartmentData(department = department)
        } catch (e: JpaObjectRetrievalFailureException) {
            logger.error("Error occurred", e)
            throw EmployeeServiceException(ErrorCodes.EMPLOYEE_NOT_FOUND)
        }
    }

    private fun convertDepartmentDataToDepartmentEntity(departmentData: DepartmentData): Department {
        return Department(
                dName = departmentData.dName,
                loc = departmentData.loc
        )
    }


    private fun convertDepartmentToDepartmentData(department: Department): DepartmentData {
        return DepartmentData(
                dName = department.dName,
                loc = department.loc,
                deptNo = department.deptNo
        )
    }
}