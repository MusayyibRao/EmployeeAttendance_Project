package com.PSL.management.response;

import lombok.Data;

import java.util.Date;

@Data
public class EmployeeAttendanceDto {

    private Long id;
    private String employeeName;
    private String employeeId;
    private String attendanceDate;
    private String entryTime;
    private String exitTime;
    private String attendanceType;
}
