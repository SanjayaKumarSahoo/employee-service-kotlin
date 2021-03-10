import java.util.*

data class DepartmentData(
        var dName: String,
        var loc: String,
)

data class EmployeeData(
        var ename: String,
        var job: String,
        var mgr: Int,
        var hireDate: Date,
        var sal: Double,
        var comm: Double,
        var department: DepartmentData
)