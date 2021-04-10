import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class DepartmentData(
        @JsonProperty("name")
        @field:NotEmpty(message = "{name.empty}")
        var dName: String,

        @JsonProperty("location")
        @field:NotEmpty(message = "{location.empty}")
        var loc: String,

        @JsonProperty("deptNo")
        var deptNo: Long?
)

data class EmployeeData(
        @JsonProperty("name")
        @field:NotEmpty(message = "{name.empty}")
        var ename: String,

        @JsonProperty("job")
        @field:NotEmpty(message = "{job.empty}")
        var job: String,

        @JsonProperty("manager")
        @field:NotNull(message = "{manager.empty}")
        var mgr: Int,

        @JsonProperty("hireDate")
        @field:NotEmpty(message = "{hireDate.empty}")
        var hireDate: String,

        @JsonProperty("salary")
        @field:NotNull(message = "{salary.empty}")
        var sal: Double,

        @JsonProperty("commission")
        @field:NotNull(message = "{commission.empty}")
        var comm: Double,

        @JsonProperty("deptNo")
        @field:NotNull(message = "{deptNo.empty}")
        var deptNo: Long,

        @JsonProperty("empNo")
        var empNo: Long?
)

data class DepartmentId(val deptNo: Long)

data class EmployeeId(val employeeId: Long)