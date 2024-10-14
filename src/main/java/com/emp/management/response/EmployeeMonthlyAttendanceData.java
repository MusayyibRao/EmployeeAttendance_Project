package com.emp.management.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeMonthlyAttendanceData {

    private Long id;
    private String employeeId;
    private String leaveNumber;
    private String absentNumber;
    private String wfhNumber;
    private String presentNumber;
    private String totalWorkingDays;
    private String month;
}
