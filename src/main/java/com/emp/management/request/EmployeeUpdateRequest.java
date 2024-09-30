package com.emp.management.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class EmployeeUpdateRequest {

    @NotBlank(message = "Employee FirstName is required")
    private String employeeFirstName;
    @NotBlank(message = "Employee LastName is required")
    private String employeeLastName;
    @NotBlank(message = "Employee Phone Number is required")
    private String employeePhoneNumber;
    @NotBlank(message = "employee email is required")
    private String employeeEmail;
    @NotBlank(message = "employee Address is required")
    private String employeeAddress;

}
