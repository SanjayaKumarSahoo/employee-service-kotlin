import com.fasterxml.jackson.annotation.JsonProperty

data class DepartmentData(
        @JsonProperty("name") var dName: String,
        @JsonProperty("location") var loc: String,
        @JsonProperty("deptNo") var deptNo: Long?
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

        @JsonProperty("deptNo")
        var deptNo: Long,

        @JsonProperty("empNo")
        var empNo: Long?
)

data class DepartmentId(val deptNo: Long)

data class EmployeeId(val employeeId: Long)

