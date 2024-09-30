package com.emp.management.bulkDataUpload;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.emp.management.dataModel.AdminEntity;
import com.emp.management.employeeModel.EmployeeAbsentEntity;
import com.emp.management.employeeModel.EmployeeAttendanceEntity;
import com.emp.management.employeeModel.EmployeeEntity;
import com.emp.management.employeeModelRepository.EmployeeAbsentRepository;
import com.emp.management.employeeModelRepository.EmployeeAttendanceDataRepository;
import com.emp.management.employeeModelRepository.EmployeeRegistrationModelRepository;
import com.emp.management.repository.AdminRepository;

@Service
public class BulkDataUploadService {

	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private EmployeeAbsentRepository employeeAbsentRepository;
	
	@Autowired
	private EmployeeAttendanceDataRepository employeeAttendanceDataRepository;

	@Autowired
	private EmployeeRegistrationModelRepository employeeRegistrationModelRepository;
	
	public void adminsave(MultipartFile file, String filename) {
		try {
			List<AdminEntity> adminEntity = AdminRegisterHelper.convertExcelToListOfProduct(file.getInputStream(), filename);
			this.adminRepository.saveAll(adminEntity);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void absentEmployeeSave(MultipartFile file, String filename) {
		try {
			List<EmployeeAbsentEntity> employeeAbsent = AbsentEmployeeHelper.convertExcelToListOfProduct(file.getInputStream(), filename);
			this.employeeAbsentRepository.saveAll(employeeAbsent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void presentEmployeeSave(MultipartFile file, String filename) {
		try {
			List<EmployeeAttendanceEntity> employeePresent = PresentEmployeeHelper.convertExcelToListOfProduct(file.getInputStream(), filename);
			this.employeeAttendanceDataRepository.saveAll(employeePresent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void registerEmployeesSave(MultipartFile file, String filename) {
		try {
			List<EmployeeEntity> employeeRegistration = RegisterEmployeesHelper.convertExcelToListOfProduct(file.getInputStream(), filename);
			this.employeeRegistrationModelRepository.saveAll(employeeRegistration);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
