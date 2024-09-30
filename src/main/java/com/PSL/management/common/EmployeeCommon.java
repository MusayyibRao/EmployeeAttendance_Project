package com.PSL.management.common;

import com.PSL.management.employeeModel.EmployeeAbsentEntity;
import com.PSL.management.employeeModel.EmployeeAttendanceEntity;
import com.PSL.management.employeeModel.EmployeeEntity;
import com.PSL.management.response.EmployeeAbsentDetailsDto;
import com.PSL.management.response.EmployeeAttendanceDto;
import com.PSL.management.response.EmployeeDto;
import org.apache.commons.codec.binary.Base64;

import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeCommon {


    public static final String ATTENDANCE_PRESENT = "PRESENT";
    public static final String ATTENDANCE_ABSENT = "ABSENT";


    public static EmployeeDto convertEmployeeEntityToEmployeeDto(EmployeeEntity employeeEntity) throws SQLException {

        EmployeeDto employeeDto = new EmployeeDto();
        if (employeeEntity != null) {
            employeeDto.setId(employeeEntity.getId());
            employeeDto.setEmployeeId(employeeEntity.getEmployeeId());
            employeeDto.setEmployeeFirstName(employeeEntity.getEmployeeFirstName());
            employeeDto.setEmployeeLastName(employeeEntity.getEmployeeLastName());
            employeeDto.setEmployeePhoneNumber(employeeEntity.getEmployeePhoneNumber());
            employeeDto.setEmployeeEmail(employeeEntity.getEmail());
            employeeDto.setEmployeeAddress(employeeEntity.getEmployeeAddress());
            String StringImageData = convertImage(employeeEntity.getEmployeeImage());
            employeeDto.setEmployeeImage(StringImageData);
            employeeDto.setEmployeeJoiningDate(employeeEntity.getEmployeeJoiningDate());
            employeeDto.setEmployeeRegistrationDate(employeeEntity.getEmployeeRegistrationDate());
            employeeDto.setEmployeeLeaveDate(employeeEntity.getEmployeeLeaveDate());
            return employeeDto;
        }
        return employeeDto;

    }

    public static EmployeeEntity convertEmployeeDtoToEmployeeEntity(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        if (employeeDto != null) {
            employeeEntity.setId(employeeDto.getId());
            employeeEntity.setEmployeeId(employeeDto.getEmployeeId());
            employeeEntity.setEmployeeFirstName(employeeDto.getEmployeeFirstName());
            employeeEntity.setEmployeeLastName(employeeDto.getEmployeeLastName());
            employeeEntity.setEmployeePhoneNumber(employeeDto.getEmployeePhoneNumber());
            employeeEntity.setEmployeeAddress(employeeDto.getEmployeeAddress());
            employeeEntity.setEmail(employeeDto.getEmployeeEmail());
            employeeEntity.setEmployeeJoiningDate(employeeDto.getEmployeeJoiningDate());
            employeeEntity.setEmployeeRegistrationDate(employeeDto.getEmployeeRegistrationDate());
        }
        return employeeEntity;
    }


    public static List<EmployeeDto> convertEmployeeListToEmployeeDtoList(List<EmployeeEntity> employeeEntityList) throws SQLException {
        List<EmployeeDto> list = new ArrayList<>();
        for (EmployeeEntity employeeEntity : employeeEntityList) {
            EmployeeDto employeeDto = convertEmployeeEntityToEmployeeDto(employeeEntity);
            list.add(employeeDto);
        }
        return list;
    }


    public static EmployeeAttendanceEntity convertEmployeeAttendanceDtoToEmployeeAttendanceEntity(EmployeeAttendanceDto employeeAttendanceDto) throws ParseException {
        EmployeeAttendanceEntity employeeAttendanceEntity = new EmployeeAttendanceEntity();
        if (employeeAttendanceDto != null) {
            employeeAttendanceEntity.setId(employeeAttendanceDto.getId());
            employeeAttendanceEntity.setEmployeeId(employeeAttendanceDto.getEmployeeId());
            employeeAttendanceEntity.setEmployeeName(employeeAttendanceDto.getEmployeeName());
            employeeAttendanceEntity.setAttendanceDate(DatePattern.formatInDate(employeeAttendanceDto.getAttendanceDate()));
            employeeAttendanceEntity.setEntryTime(DatePattern.timeFormatInDate(employeeAttendanceDto.getEntryTime()));
            employeeAttendanceEntity.setExitTime(DatePattern.timeFormatInDate(employeeAttendanceDto.getExitTime()));
            employeeAttendanceEntity.setAttendanceType(employeeAttendanceDto.getAttendanceType());
        }
        return employeeAttendanceEntity;
    }

    public static EmployeeAttendanceDto convertEmployeeAttendanceEntityToEmployeeAttendanceDto(EmployeeAttendanceEntity employeeAttendanceEntity) throws ParseException {

        EmployeeAttendanceDto employeeAttendanceDto = new EmployeeAttendanceDto();
        if (employeeAttendanceEntity != null) {
            employeeAttendanceDto.setId(employeeAttendanceEntity.getId());
            employeeAttendanceDto.setEmployeeId(employeeAttendanceEntity.getEmployeeId());
            employeeAttendanceDto.setEmployeeName(employeeAttendanceEntity.getEmployeeName());
            employeeAttendanceDto.setAttendanceDate(DatePattern.formatDate(employeeAttendanceEntity.getAttendanceDate()));
            employeeAttendanceDto.setEntryTime(DatePattern.timeFormat(employeeAttendanceEntity.getEntryTime()));
            employeeAttendanceDto.setExitTime(DatePattern.timeFormat(employeeAttendanceEntity.getExitTime()));
            employeeAttendanceDto.setAttendanceType(employeeAttendanceEntity.getAttendanceType());
        }
        return employeeAttendanceDto;
    }

    public static List<EmployeeAttendanceDto> convertEmpAttendanceToDto(List<EmployeeAttendanceEntity> employeeAttendanceEntities) throws ParseException {
        List<EmployeeAttendanceDto> list = new ArrayList<>();
        for (EmployeeAttendanceEntity employeeAttendanceEntity : employeeAttendanceEntities) {
            EmployeeAttendanceDto employeeAttendanceDto = convertEmployeeAttendanceEntityToEmployeeAttendanceDto(employeeAttendanceEntity);
            list.add(employeeAttendanceDto);
        }
        return list;
//          return employeeAttendanceEntities.stream().map(EmployeeCommon::convertEmployeeAttendanceEntityToEmployeeAttendanceDto).collect(Collectors.toList());
    }

    public static EmployeeAbsentEntity convertEmpAbsentDtoToEmpAbsentEntity(EmployeeAbsentDetailsDto employeeAbsentDetailsDto) throws ParseException {

        EmployeeAbsentEntity employeeAbsent = new EmployeeAbsentEntity();
        if (employeeAbsentDetailsDto != null) {
            employeeAbsent.setEmployeeId(employeeAbsentDetailsDto.getEmployeeId());
            employeeAbsent.setEmployeeName(employeeAbsentDetailsDto.getEmployeeName());
            employeeAbsent.setEmloyeeAbsentDate(DatePattern.formatInDate(employeeAbsentDetailsDto.getEmployeeAbsentDate()));
            employeeAbsent.setLeaveReason(employeeAbsentDetailsDto.getLeaveReason());
        }
        return employeeAbsent;
    }

    public static EmployeeAbsentDetailsDto convertEmpAbsentEntityToEmpAbsentDto(EmployeeAbsentEntity employeeAbsentEntity) throws ParseException {

        EmployeeAbsentDetailsDto employeeAbsentDetailsDto = new EmployeeAbsentDetailsDto();
        if (employeeAbsentEntity != null) {
            employeeAbsentDetailsDto.setEmployeeId(employeeAbsentEntity.getEmployeeId());
            employeeAbsentDetailsDto.setEmployeeName(employeeAbsentEntity.getEmployeeName());
            employeeAbsentDetailsDto.setEmployeeAbsentDate(DatePattern.formatDate(employeeAbsentEntity.getEmloyeeAbsentDate()));
            employeeAbsentDetailsDto.setLeaveReason(employeeAbsentEntity.getLeaveReason());
        }
        return employeeAbsentDetailsDto;
    }


    private static String convertImage(Blob employeeImage) throws SQLException {
        byte[] byteImage = employeeImage.getBytes(1, (int) employeeImage.length());

        if (byteImage != null) {
            String imageString = Base64.encodeBase64String(byteImage);
            return imageString;
        }
        return null;
    }
}
