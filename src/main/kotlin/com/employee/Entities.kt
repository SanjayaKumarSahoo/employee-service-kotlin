package com.employee

import java.util.*
import javax.persistence.*


@Entity
@Table(name = "EMP", catalog = "SCOTT")
class Employee(
    @Column(name = "ENAME") var ename: String,
    @Column(name = "JOB") var job: String,
    @Column(name = "MGR") var mgr: Int,
    @Temporal(TemporalType.DATE) @Column(name = "HIREDATE") var hireDate: Date,
    @Column(name = "SAL") var sal: Double,
    @Column(name = "COMM") var comm: Double,
    @ManyToOne(fetch = FetchType.LAZY) var department: Department,
    @Id @GeneratedValue @Column(name = "EMPNO") var empno: Long? = null
)

@Entity
@Table(name = "DEPT", catalog = "SCOTT")
class Department(
    @Id @GeneratedValue @Column(name = "DEPTNO") var deptNo: Int? = null,
    var dName: String,
    var loc: String,
)