package com.emp.management.service;

import java.util.List;

import com.emp.management.request.LoginRequest;
import com.emp.management.response.AdminResponse;
import com.emp.management.response.RegisterDto;
import com.emp.management.employeeModel.EmployeeEntity;
import org.springframework.stereotype.Service;

import com.emp.management.dataModel.AdminEntity;

@Service
public interface AdminService {


    List<EmployeeEntity> findEmployeePagination(int pageNo, int pageSize);

/*
    List<EmployeeAbsentDetails> findEmployeeAbsentPage(int pageNo, int pageSize);
*/

/*
    List<EmployeeAttendanceEntity> findEmployeePresentPage(int pageNo, int pageSize);
*/

/*
    List<EmployeeMonthlyStatusEntity> findEmployeeMonthlyStatusPage(int pageNo, int PageSize);
*/

    AdminResponse login(LoginRequest loginRequest);

    AdminResponse addUser(AdminEntity adminEntity);

    AdminResponse getAllAdmin();

    AdminResponse getUser(String username);

    AdminResponse deleteUser(String email);

    AdminResponse updateUser(RegisterDto register, String email);


}
