package com.emp.management.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeResponse {

    private int statusCode;
    private String message;
    private EmployeeDto employeeData;
    private List<EmployeeDto> employeeDataList;
    private EmployeeAbsentDetailsDto employeeAbsentDetails;
    private EmployeeAttendanceDto employeeAttendanceDetails;
    private List<EmployeeAbsentDetailsDto> employeeAbsentDetailsList;
    private List<EmployeeAttendanceDto> employeeAttendanceDetailsList;




}
