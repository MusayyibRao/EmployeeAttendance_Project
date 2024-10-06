package com.emp.management.employeeService;

import com.emp.management.request.EmployeeUpdateRequest;
import com.emp.management.response.EmployeeResponse;
import com.emp.management.employeeModel.EmployeeAbsentEntity;
import com.emp.management.employeeModel.EmployeeMonthlyStatusEntity;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;


public interface EmployeeService {

    EmployeeResponse addEmployee(String employeeFirstName,String employeeLastName,String employeePhoneNumber,String email, String employeeAddress, MultipartFile employeeImage);

    EmployeeResponse getAllEmployees();

    EmployeeResponse getEmployeeDetailsByEmployeeId(String employeeId);

    EmployeeResponse updateEmployeeDetails(EmployeeUpdateRequest employeeUpdateRequest,MultipartFile employeeImage,String employeeId);

    EmployeeResponse deleteEmployee(String employeeId);

    EmployeeResponse addEmployeeAttendanceDetails(String employeeId,String attendanceType,String leaveReason);

/*
    EmployeeAbsentEntity addEmployeeAbsentDetails(EmployeeAbsentEntity employeeAbsentEntity);
*/

    EmployeeResponse getEmployeeBySearch(String anyDetails);

    EmployeeMonthlyStatusEntity addEmployeeMonthlyAttendanceDetails(EmployeeMonthlyStatusEntity employeeMonthlyStatusEntity);

    EmployeeMonthlyStatusEntity updateEmployeeMonthlyDetails(EmployeeMonthlyStatusEntity employeeMonthlyStatusEntity);

    EmployeeResponse employeeExitTime(String employeeId) throws ParseException;
}
