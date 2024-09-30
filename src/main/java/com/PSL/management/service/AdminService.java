package com.PSL.management.service;

import java.util.List;

import com.PSL.management.request.LoginRequest;
import com.PSL.management.response.AdminResponse;
import com.PSL.management.response.RegisterDto;
import org.springframework.stereotype.Service;

import com.PSL.management.dataModel.AdminEntity;
import com.PSL.management.employeeModel.EmployeeEntity;

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
