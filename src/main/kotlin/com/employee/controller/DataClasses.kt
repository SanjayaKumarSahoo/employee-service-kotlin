import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotEmpty

data class DepartmentData(
    @JsonProperty("name")
    @NotEmpty(message = "{name.empty}")
    var dName: String,

    @JsonProperty("location")
    @NotEmpty(message = "{location.empty")
    var loc: String,

    @JsonProperty("deptNo")
    var deptNo: Long?
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

