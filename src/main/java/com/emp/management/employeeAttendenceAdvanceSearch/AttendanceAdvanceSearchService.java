package com.emp.management.employeeAttendenceAdvanceSearch;

import com.emp.management.response.EmployeeResponse;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceAdvanceSearchService {
	EmployeeResponse employeesAttendanceList(String empIdOrName);
}
