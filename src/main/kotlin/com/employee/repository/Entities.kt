package com.employee.repository

import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType


@Entity
@Table(name = "EMP", catalog = "SCOTT")
class Employee(
    @Column(name = "ENAME")
    var ename: String,

    @Column(name = "JOB")
    var job: String,

    @Column(name = "MGR")
    var mgr: Int,

    @Temporal(TemporalType.DATE) @Column(name = "HIREDATE")
    var hireDate: Date,

    @Column(name = "SAL")
    var sal: Double,

    @Column(name = "COMM")
    var comm: Double,

    @ManyToOne(fetch = FetchType.LAZY)
    var department: Department,

    @Id @GeneratedValue @Column(name = "EMPNO")
    var empno: Long? = null
)

@Entity
@Table(name = "DEPT", catalog = "SCOTT")
class Department(
    @Id @GeneratedValue @Column(name = "DEPTNO")
    var deptNo: Long? = null,

    @Column(name = "DEPT_NAME")
    var dName: String,

    @Column(name = "LOCATION")
    var loc: String,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    var employees: MutableList<Employee> = arrayListOf()
)

