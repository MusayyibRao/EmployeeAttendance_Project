package com.PSL.management.employeeAttendenceAdvanceSearch;

import java.util.List;

import com.PSL.management.common.EmployeeCommon;
import com.PSL.management.employeeModel.EmployeeAttendanceEntity;
import com.PSL.management.employeeModelRepository.EmployeeAttendanceDataRepository;
import com.PSL.management.response.EmployeeAttendanceDto;
import com.PSL.management.response.EmployeeResponse;
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
