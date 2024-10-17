package com.emp.management.common;

import com.emp.management.employeeModel.EmployeeAbsentEntity;
import com.emp.management.employeeModel.EmployeeAttendanceEntity;
import com.emp.management.employeeModel.EmployeeEntity;
import com.emp.management.employeeModel.EmployeeMonthlyStatusEntity;
import com.emp.management.response.EmployeeAbsentDetailsDto;
import com.emp.management.response.EmployeeAttendanceDto;
import com.emp.management.response.EmployeeDto;
import com.emp.management.response.EmployeeMonthlyAttendanceData;
import org.apache.commons.codec.binary.Base64;

import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        try {
            for (EmployeeEntity employeeEntity : employeeEntityList) {
                EmployeeDto employeeDto = convertEmployeeEntityToEmployeeDto(employeeEntity);
                list.add(employeeDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        try {
            for (EmployeeAttendanceEntity employeeAttendanceEntity : employeeAttendanceEntities) {
                EmployeeAttendanceDto employeeAttendanceDto = convertEmployeeAttendanceEntityToEmployeeAttendanceDto(employeeAttendanceEntity);
                list.add(employeeAttendanceDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
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


    public static EmployeeMonthlyAttendanceData convertEmployeeMonthlyEntityToDto(EmployeeMonthlyStatusEntity employeeMonthlyStatusEntity) {
        EmployeeMonthlyAttendanceData employeeMonthlyAttendanceData = new EmployeeMonthlyAttendanceData();
        try {
            if (employeeMonthlyStatusEntity != null) {
                employeeMonthlyAttendanceData.setId(employeeMonthlyStatusEntity.getId());
                employeeMonthlyAttendanceData.setEmployeeId(employeeMonthlyStatusEntity.getEmployeeId());
                employeeMonthlyAttendanceData.setPresentNumber(employeeMonthlyStatusEntity.getPresentNumber());
                employeeMonthlyAttendanceData.setAbsentNumber(employeeMonthlyStatusEntity.getAbsentNumber());
                employeeMonthlyAttendanceData.setTotalWorkingDays(employeeMonthlyStatusEntity.getTotalWorkingDays());
                employeeMonthlyAttendanceData.setWfhNumber(employeeMonthlyStatusEntity.getWfhNumber());
                employeeMonthlyAttendanceData.setLeaveNumber(employeeMonthlyStatusEntity.getLeaveNumber());
                employeeMonthlyAttendanceData.setMonth(getOnlyMonth(employeeMonthlyStatusEntity.getMonthYear()));
                return employeeMonthlyAttendanceData;
            }
        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
        return employeeMonthlyAttendanceData;
    }

    public static EmployeeMonthlyStatusEntity convertEmployeeMonthlyDtoToEntity(EmployeeMonthlyAttendanceData employeeMonthlyAttendanceData) {
        EmployeeMonthlyStatusEntity employeeMonthlyStatusEntity = new EmployeeMonthlyStatusEntity();
        if (employeeMonthlyAttendanceData != null) {
        }
        return null;
    }

    public static List<EmployeeMonthlyAttendanceData> convertEmployeeMonthlyEntityToDtoList(List<EmployeeMonthlyStatusEntity> employeeMonthlyStatusEntityList) {
        List<EmployeeMonthlyAttendanceData> list = new ArrayList<>();
        try {
            for (EmployeeMonthlyStatusEntity employeeMonthlyStatusEntity : employeeMonthlyStatusEntityList) {
                EmployeeMonthlyAttendanceData employeeMonthlyAttendanceData = convertEmployeeMonthlyEntityToDto(employeeMonthlyStatusEntity);
                list.add(employeeMonthlyAttendanceData);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return list;

        // return employeeMonthlyStatusEntityList.stream().map(EmployeeCommon::convertEmployeeMonthlyEntityToDto).collect(Collectors.toList());
    }

    public static String getOnlyMonth(Date date) {
        String month = DatePattern.getMonth(date);
        return CommonMonthsName.getMonthName(month);
    }
}

