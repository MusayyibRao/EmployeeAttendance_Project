package com.PSL.management.employeeAttendenceAdvanceSearch;

import java.util.List;

import com.PSL.management.response.EmployeeResponse;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceAdvanceSearchService {
	EmployeeResponse employeesAttendanceList(String empIdOrName);
}
