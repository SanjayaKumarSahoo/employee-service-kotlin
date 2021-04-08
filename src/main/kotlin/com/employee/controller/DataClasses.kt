import com.fasterxml.jackson.annotation.JsonProperty

data class DepartmentData(
        @JsonProperty("name") var dName: String,
        @JsonProperty("location") var loc: String,
        @JsonProperty("departmentNo") var deptNo: Long?
)

data class EmployeeData(
        @JsonProperty("name")
        var ename: String,

        @JsonProperty("job")
        var job: String,

        @JsonProperty("manager")
        var mgr: Int,

        @JsonProperty("hireDate")
        var hireDate: String,

        @JsonProperty("salary")
        var sal: Double,

        @JsonProperty("commission")
        var comm: Double,

        @JsonProperty("department")
        var department: DepartmentData
)

data class DepartmentId(val deptNo: Long)

data class EmployeeId(val employeeId: Long)

