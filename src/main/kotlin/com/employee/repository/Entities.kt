package com.employee.repository

import java.util.*
import javax.persistence.*


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

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_generator")
        @SequenceGenerator(name = "emp_generator", sequenceName = "emp_seq")
        @Column(name = "EMPNO")
        var empno: Long? = null
)

@Entity
@Table(name = "DEPT", catalog = "SCOTT")
class Department(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dept_generator")
        @SequenceGenerator(name = "dept_generator", sequenceName = "dept_seq")
        @Column(name = "DEPTNO")
        var deptNo: Long? = null,

        @Column(name = "DEPT_NAME")
        var dName: String,

        @Column(name = "LOCATION")
        var loc: String,

        @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
        var employees: MutableList<Employee> = arrayListOf()
)

