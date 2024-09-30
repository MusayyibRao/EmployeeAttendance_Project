package com.PSL.management.service;

import java.util.List;

import com.PSL.management.request.LoginRequest;
import com.PSL.management.Security.JWTUtils;
import com.PSL.management.common.AdminCommon;
import com.PSL.management.response.AdminResponse;
import com.PSL.management.response.RegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.PSL.management.dataModel.AdminEntity;
import com.PSL.management.employeeModel.EmployeeEntity;
import com.PSL.management.employeeModelRepository.EmployeeRegistrationModelRepository;
import com.PSL.management.repository.AdminRepository;

@Service
public class AdminServiceImp implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EmployeeRegistrationModelRepository employeeRegistrationModelRepository;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<EmployeeEntity> findEmployeePagination(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<EmployeeEntity> pageResult = employeeRegistrationModelRepository.findAll(paging);
        return pageResult.toList();
    }

  /*  @Override
    public List<EmployeeAbsentDetails> findEmployeeAbsentPage(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<EmployeeAbsentDetails> pageResult = employeeAbsentRepository.findAll(paging);
        return pageResult.toList();
    }*/


  /*  @Override
    public List<EmployeeMonthlyStatusEntity> findEmployeeMonthlyStatusPage(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<EmployeeMonthlyStatusEntity> pageResult = employeeMonthlyStatusRepository.findAll(paging);
        return pageResult.toList();
    }*/

  /*  @Override
    public List<EmployeeResponse> findEmployeePresentPage(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<EmployeeResponse> pageResult = employeeAttendanceDataRepository.findAll(paging);
        return pageResult.toList();
    }*/

    @Override
    public AdminResponse login(LoginRequest loginRequest) {

        AdminResponse response = new AdminResponse();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            var admin = adminRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException("user not found"));
            var jwtCode = jwtUtils.generateToken(admin);
            response.setStatusCode(200);
            response.setToken(jwtCode);
            response.setExpirationTime("7 Days");
            response.setMessage("Login Successfully");
        } catch (Exception e) {
            response.setStatusCode(400);
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    public AdminResponse addUser(AdminEntity adminEntity) {

        AdminResponse response = new AdminResponse();
        try {
            if (adminEntity.getRole() == null || adminEntity.getRole().isBlank()) {
                adminEntity.setRole("ADMIN");
            }
            if (adminRepository.existsByEmail(adminEntity.getEmail())) {
                throw new RuntimeException(adminEntity.getEmail() + " Already Exists !");
            }
            adminEntity.setPassword(passwordEncoder.encode(adminEntity.getPassword()));
            AdminEntity adminEntityAdmin = adminRepository.save(adminEntity);
            RegisterDto registerData = AdminCommon.convertRegisterToRegisterDto(adminEntityAdmin);
            response.setStatusCode(200);
            response.setAdminData(registerData);
        } catch (Exception e) {
            response.setStatusCode(400);
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    public AdminResponse getUser(String username) {
        AdminResponse response = new AdminResponse();
        try {
            AdminEntity adminEntity = adminRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User Not Found"));
            RegisterDto registerDto = AdminCommon.convertRegisterToRegisterDto(adminEntity);
            response.setStatusCode(200);
            response.setMessage("successfull");
            response.setAdminData(registerDto);
        } catch (Exception e) {
            response.setStatusCode(400);
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    public AdminResponse deleteUser(String email) {

        AdminResponse response = new AdminResponse();
        try {
            adminRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

            adminRepository.deleteByEmail(email);
            response.setStatusCode(200);
            response.setMessage("user Deleted successfully !");
        } catch (Exception e) {
            response.setStatusCode(400);
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    public AdminResponse updateUser(RegisterDto register, String email) {
        AdminResponse response = new AdminResponse();
        try {
            AdminEntity adminEntityData = adminRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("user not found"));
            if (adminRepository.existsByEmail(register.getEmail())) {
                throw new RuntimeException(register.getEmail() + " Already Exists !");
            }
            if (adminEntityData != null) {
                register.setCreatedBy(adminEntityData.getCreatedBy());
                register.setEmail(adminEntityData.getEmail());
                register.setCreatedDate(adminEntityData.getCreatedDate());
                register.setRole(adminEntityData.getRole());
                register.setId(adminEntityData.getId());
                adminEntityData = AdminCommon.convertRegisterDtoToRegister(register);
                adminEntityData.setPassword(passwordEncoder.encode(register.getPassword()));
                AdminEntity adminEntity1 = adminRepository.save(adminEntityData);
                RegisterDto registerDto = AdminCommon.convertRegisterToRegisterDto(adminEntity1);
                response.setStatusCode(200);
                response.setMessage("update Successfully !");
                response.setAdminData(registerDto);
                return response;
            }
        } catch (Exception e) {

            response.setStatusCode(400);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public AdminResponse getAllAdmin() {
        AdminResponse response = new AdminResponse();
        try {
            List<AdminEntity> adminEntityList = adminRepository.findAll();
            List<RegisterDto> registerDtoList = AdminCommon.convertRegisterToRegisterDtoList(adminEntityList);
            response.setStatusCode(200);
            response.setMessage("successfull");
            response.setAdminDataList(registerDtoList);
        } catch (Exception e) {
            response.setStatusCode(400);
            response.setMessage(e.getMessage());
        }
        return response;
    }


}
