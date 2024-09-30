package com.PSL.management.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeAbsentDetailsDto {

    private String employeeName;
    private String employeeId;
    private String leaveReason;
    private String employeeAbsentDate;
}
