package com.PSL.management.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDto {
    private Long id;
    private String employeeId;
    private String EmployeeFirstName;
    private String EmployeeLastName;
    private String EmployeePhoneNumber;
    private String EmployeeEmail;
    private String EmployeeAddress;
    private Date EmployeeRegistrationDate;
    private Date EmployeeJoiningDate;
    private Date EmployeeLeaveDate;
    private String EmployeeImage;
}
