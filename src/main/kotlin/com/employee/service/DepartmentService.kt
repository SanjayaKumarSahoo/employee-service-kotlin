package com.employee.service

import DepartmentData
import com.employee.error.ErrorCodes
import com.employee.exception.EmployeeServiceException
import com.employee.repository.Department
import com.employee.repository.DepartmentRepository
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException


data class DepartmentId(val departmentId: Long)

interface IDepartmentService {
    fun createDepartment(departmentData: DepartmentData): DepartmentId?

    fun getDepartmentById(id: Long): DepartmentData
}

open class DepartmentServiceImpl(private val departmentRepository: DepartmentRepository) : IDepartmentService {

    @Transactional
    override fun createDepartment(departmentData: DepartmentData): DepartmentId? {
        val department = convertDepartmentDataToDepartmentEntity(departmentData)
        return departmentRepository.save(department).deptNo?.let { DepartmentId(departmentId = it) };
    }

    @Transactional(readOnly = true)
    override fun getDepartmentById(id: Long): DepartmentData {
        try {
            val department = departmentRepository.getOne(id)
            return convertDepartmentToDepartmentData(department = department)
        } catch (e: EntityNotFoundException) {
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