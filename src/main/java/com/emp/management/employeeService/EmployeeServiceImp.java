package com.emp.management.employeeService;

import com.emp.management.common.DatePattern;
import com.emp.management.common.EmployeeCommon;
import com.emp.management.employeeModel.EmployeeAbsentEntity;
import com.emp.management.employeeModel.EmployeeAttendanceEntity;
import com.emp.management.employeeModel.EmployeeMonthlyStatusEntity;
import com.emp.management.employeeModel.EmployeeEntity;
import com.emp.management.employeeModelRepository.EmployeeAbsentRepository;
import com.emp.management.employeeModelRepository.EmployeeAttendanceDataRepository;
import com.emp.management.employeeModelRepository.EmployeeMonthlyStatusRepository;
import com.emp.management.employeeModelRepository.EmployeeRegistrationModelRepository;
import com.emp.management.request.EmployeeUpdateRequest;
import com.emp.management.response.EmployeeAbsentDetailsDto;
import com.emp.management.response.EmployeeAttendanceDto;
import com.emp.management.response.EmployeeDto;
import com.emp.management.response.EmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    private EmployeeRegistrationModelRepository employeeRepository;

    @Autowired
    private EmployeeAttendanceDataRepository employeeAttendanceDataRepository;

    @Autowired
    private EmployeeAbsentRepository employeeAbsentRepository;

    @Autowired
    private EmployeeMonthlyStatusRepository employeeMonthlyStatusRepository;


    @Override
    public EmployeeResponse addEmployee(String employeeFirstName, String employeeLastName, String employeePhoneNumber, String email, String employeeAddress, MultipartFile employeeImage) {

        EmployeeResponse response = new EmployeeResponse();
        try {

            Long employeeId = employeeRepository.maxId().orElse(0L);
            EmployeeEntity entityModel = new EmployeeEntity();
            entityModel.setEmployeeId("MR" + (employeeId + 1));
            entityModel.setEmployeeFirstName(employeeFirstName);
            entityModel.setEmployeeLastName(employeeLastName);
            entityModel.setEmployeePhoneNumber(employeePhoneNumber);
            entityModel.setEmployeeAddress(employeeAddress);
            entityModel.setEmail(email);
            if (!employeeImage.isEmpty()) {
                byte[] byteData = employeeImage.getBytes();
                Blob imageInBlob = new SerialBlob(byteData);
                entityModel.setEmployeeImage(imageInBlob);

            }
            entityModel.setEmployeeRegistrationDate(new Date());
            entityModel.setEmployeeJoiningDate(new Date());
            EmployeeEntity saveData = employeeRepository.save(entityModel);
            EmployeeDto employeeDto = EmployeeCommon.convertEmployeeEntityToEmployeeDto(saveData);
            response.setStatusCode(200);
            response.setMessage("Employee Add Successfully !");
            response.setEmployeeData(employeeDto);
            return response;

        } catch (Exception e) {
            response.setStatusCode(400);
            response.setMessage(e.getMessage());

        }

        return response;
    }

    @Override
    public EmployeeResponse getAllEmployees() {

        EmployeeResponse response = new EmployeeResponse();
        try {
            List<EmployeeEntity> employeeList = employeeRepository.findAll();
            List<EmployeeDto> employeeDtoList = EmployeeCommon.convertEmployeeListToEmployeeDtoList(employeeList);
            response.setStatusCode(200);
            response.setMessage("successfully");
            response.setEmployeeDataList(employeeDtoList);
        } catch (Exception e) {
            response.setStatusCode(400);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public EmployeeResponse getEmployeeDetailsByEmployeeId(String employeeId) {

        EmployeeResponse response = new EmployeeResponse();
        try {
            EmployeeEntity registrationEntityModel = employeeRepository.findByEmployeeId(employeeId).orElseThrow(() -> new RuntimeException("Employee not Exists!"));
            EmployeeDto employeeDto = EmployeeCommon.convertEmployeeEntityToEmployeeDto(registrationEntityModel);
            response.setStatusCode(200);
            response.setMessage("successfully !");
            response.setEmployeeData(employeeDto);
        } catch (Exception e) {
            response.setStatusCode(400);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public EmployeeResponse updateEmployeeDetails(EmployeeUpdateRequest employeeUpdateRequest, MultipartFile employeeImage, String employeeId) {

        EmployeeResponse response = new EmployeeResponse();
        EmployeeDto employeeDto = new EmployeeDto();

        try {
            EmployeeEntity employeeEntityData = employeeRepository.findByEmployeeId(employeeId).orElseThrow(() -> new RuntimeException("employee not exists !"));
            if (employeeRepository.existsByEmail(employeeUpdateRequest.getEmployeeEmail())) {
                throw new RuntimeException("Employee Email Already Exists !");
            }
            if (employeeEntityData != null) {
                employeeDto.setId(employeeEntityData.getId());
                employeeDto.setEmployeeId(employeeId);
                employeeDto.setEmployeeFirstName(employeeUpdateRequest.getEmployeeFirstName());
                employeeDto.setEmployeeLastName(employeeUpdateRequest.getEmployeeLastName());
                employeeDto.setEmployeePhoneNumber(employeeUpdateRequest.getEmployeePhoneNumber());
                employeeDto.setEmployeeEmail(employeeUpdateRequest.getEmployeeEmail());
                employeeDto.setEmployeeAddress(employeeUpdateRequest.getEmployeeAddress());
                employeeDto.setEmployeeJoiningDate(employeeEntityData.getEmployeeJoiningDate());
                employeeDto.setEmployeeRegistrationDate(employeeEntityData.getEmployeeRegistrationDate());
            }
            employeeEntityData = EmployeeCommon.convertEmployeeDtoToEmployeeEntity(employeeDto);

            if (!employeeImage.isEmpty()) {
                byte[] byteData = employeeImage.getBytes();
                Blob imageInBlob = new SerialBlob(byteData);
                employeeEntityData.setEmployeeImage(imageInBlob);
            }
            EmployeeEntity employeeEntityUpdate = employeeRepository.save(employeeEntityData);
            EmployeeDto employeeDto1 = EmployeeCommon.convertEmployeeEntityToEmployeeDto(employeeEntityUpdate);
            response.setStatusCode(200);
            response.setMessage("Employee Data Updated Successfully !");
            response.setEmployeeData(employeeDto1);
        } catch (Exception e) {
            response.setStatusCode(200);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public EmployeeResponse deleteEmployee(String employeeId) {

        EmployeeResponse response = new EmployeeResponse();
        try {
            EmployeeEntity employeeData = employeeRepository.findByEmployeeId(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
            if (employeeData != null) {
                employeeRepository.deleteByEmployeeId(employeeId);
                response.setStatusCode(200);
                response.setMessage("Employee Data deleted successfully !");
            }
        } catch (Exception e) {
            response.setStatusCode(400);
            response.setMessage(e.getMessage());
        }


        return response;
    }

    @Override
    public EmployeeResponse addEmployeeAttendanceDetails(String employeeId, String attendanceType, String leaveReason) {
        EmployeeResponse response = new EmployeeResponse();
        try {
            EmployeeEntity employeeEntity = employeeRepository.findByEmployeeId(employeeId).orElseThrow(() -> new RuntimeException("Employee not exists !"));
            if (employeeEntity != null) {
                EmployeeAttendanceDto employeeAttendanceDto = new EmployeeAttendanceDto();
                employeeAttendanceDto.setEmployeeId(employeeId);
//                employeeAttendanceDto.setId(employeeEntity.getId());
                employeeAttendanceDto.setEmployeeName(employeeEntity.getEmployeeFirstName() + " " + employeeEntity.getEmployeeLastName());
                Date date = new Date();
                employeeAttendanceDto.setAttendanceDate(DatePattern.formatDate(date));
                if (attendanceType.equalsIgnoreCase("P")) {
                    employeeAttendanceDto.setEntryTime(DatePattern.timeFormat(date));
                    employeeAttendanceDto.setAttendanceType(EmployeeCommon.ATTENDANCE_PRESENT);
                    System.out.println("employee Attendance Dto: " + employeeAttendanceDto);

                } else {
                    employeeAttendanceDto.setAttendanceType(EmployeeCommon.ATTENDANCE_ABSENT);
                    EmployeeAbsentDetailsDto employeeAbsentDetailsDto = new EmployeeAbsentDetailsDto();
                    employeeAbsentDetailsDto.setEmployeeId(employeeId);
                    employeeAbsentDetailsDto.setEmployeeName(employeeEntity.getEmployeeFirstName() + " " + employeeEntity.getEmployeeLastName());
                    employeeAbsentDetailsDto.setEmployeeAbsentDate(DatePattern.formatDate(date));
                    if (!leaveReason.isBlank() || !leaveReason.isEmpty()) {
                        employeeAbsentDetailsDto.setLeaveReason(leaveReason);
                    } else {
                        employeeAbsentDetailsDto.setLeaveReason("No Reason");
                    }
                    EmployeeAbsentEntity employeeAbsent = EmployeeCommon.convertEmpAbsentDtoToEmpAbsentEntity(employeeAbsentDetailsDto);
                    if (employeeAbsentRepository.ExistsByAbsentDate(employeeAbsent.getEmloyeeAbsentDate())) {
                        throw new RuntimeException("Employee Attendance Recorded Already !");
                    }
                    EmployeeAbsentEntity employeeAbsentSave = employeeAbsentRepository.save(employeeAbsent);
                    EmployeeAbsentDetailsDto employeeAbsentDetailsDto1 = EmployeeCommon.convertEmpAbsentEntityToEmpAbsentDto(employeeAbsentSave);
                    response.setEmployeeAbsentDetails(employeeAbsentDetailsDto1);
                }
                EmployeeAttendanceEntity employeeAttendanceEntity = EmployeeCommon.convertEmployeeAttendanceDtoToEmployeeAttendanceEntity(employeeAttendanceDto);
                System.out.println("dateFormat: " + employeeAttendanceEntity.getAttendanceDate());
                System.out.println("existsBy :" + employeeAttendanceDataRepository.ExistsByAttendanceDate(employeeAttendanceEntity.getAttendanceDate()));
                if (employeeAttendanceDataRepository.ExistsByAttendanceDate(employeeAttendanceEntity.getAttendanceDate())) {
                    throw new RuntimeException("Employee Attendance Recorded Already !");
                }
                EmployeeAttendanceEntity saveAttendance = employeeAttendanceDataRepository.save(employeeAttendanceEntity);
                System.out.println("saveAtt:: " + saveAttendance);
                EmployeeAttendanceDto attendanceDto = EmployeeCommon.convertEmployeeAttendanceEntityToEmployeeAttendanceDto(saveAttendance);
                if (attendanceType.equalsIgnoreCase("P")) {
                    response.setEmployeeAttendanceDetails(attendanceDto);
                }
                response.setStatusCode(200);
                response.setMessage("Add Attendance Successfully !");
            }
        } catch (Exception e) {
            response.setStatusCode(400);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public EmployeeResponse getEmployeeBySearch(String anyDetails) {
        EmployeeResponse response = new EmployeeResponse();
        try {
            List<EmployeeEntity> employeeEntities = employeeRepository.getEmployeeBySearching(anyDetails);
            List<EmployeeDto> employeeDtoList = EmployeeCommon.convertEmployeeListToEmployeeDtoList(employeeEntities);
            response.setStatusCode(200);
            response.setMessage("successfully !");
            response.setEmployeeDataList(employeeDtoList);
        } catch (Exception e) {
            response.setStatusCode(400);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public EmployeeAbsentEntity addEmployeeAbsentDetails(EmployeeAbsentEntity employeeAbsentEntity) {
        return null;
    }


    @Override
    public EmployeeMonthlyStatusEntity addEmployeeMonthlyAttendanceDetails(EmployeeMonthlyStatusEntity employeeMonthlyStatusEntity) {
        return null;
    }

    @Override
    public EmployeeMonthlyStatusEntity updateEmployeeMonthlyDetails(EmployeeMonthlyStatusEntity employeeMonthlyStatusEntity) {
        return null;
    }


}
