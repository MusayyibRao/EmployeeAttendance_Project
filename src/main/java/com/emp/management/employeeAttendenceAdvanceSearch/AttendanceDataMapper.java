package com.emp.management.employeeAttendenceAdvanceSearch;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.emp.management.employeeModel.EmployeeAttendanceEntity;
import org.springframework.jdbc.core.RowMapper;

public class AttendanceDataMapper implements RowMapper<EmployeeAttendanceEntity>{

	@Override
	public EmployeeAttendanceEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmployeeAttendanceEntity employeeAttendanceEntity = new EmployeeAttendanceEntity();
		employeeAttendanceEntity.setId(rs.getLong("id"));
		employeeAttendanceEntity.setEmployeeId(rs.getString("employeeid"));
		employeeAttendanceEntity.setEmployeeName(rs.getString("employeename"));
		employeeAttendanceEntity.setAttendanceDate(rs.getDate("attendencedate"));
		employeeAttendanceEntity.setEntryTime(rs.getDate("entrytime"));
		employeeAttendanceEntity.setExitTime(rs.getDate("exittime"));
		return employeeAttendanceEntity;
	}

}
