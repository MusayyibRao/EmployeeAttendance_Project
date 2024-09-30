package com.emp.management.employeeAttendenceAdvanceSearch;

import java.util.List;

import com.emp.management.common.EmployeeCommon;
import com.emp.management.employeeModel.EmployeeAttendanceEntity;
import com.emp.management.employeeModelRepository.EmployeeAttendanceDataRepository;
import com.emp.management.response.EmployeeAttendanceDto;
import com.emp.management.response.EmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceAdvanceSearchServiceImp implements AttendanceAdvanceSearchService {

    @Autowired
    EmployeeAttendanceDataRepository employeeAttendanceDataRepository;

    @Override
    public EmployeeResponse employeesAttendanceList(String empIdOrName) {
        EmployeeResponse response = new EmployeeResponse();
        try {
            List<EmployeeAttendanceEntity> employeeAttendanceEntity = employeeAttendanceDataRepository.getEmployeeAttendanceBySearching(empIdOrName);
            if (employeeAttendanceEntity != null) {
                List<EmployeeAttendanceDto> employeeAttendanceDtoList = EmployeeCommon.convertEmpAttendanceToDto(employeeAttendanceEntity);
                response.setStatusCode(200);
                response.setMessage("successfully");
                response.setEmployeeAttendanceDetailsList(employeeAttendanceDtoList);
            }
        } catch (Exception e) {
            response.setStatusCode(400);
            response.setMessage(e.getMessage());
        }
        return response;
    }

}
