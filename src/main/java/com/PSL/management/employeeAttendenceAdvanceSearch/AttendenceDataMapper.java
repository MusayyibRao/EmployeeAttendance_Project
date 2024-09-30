package com.PSL.management.employeeAttendenceAdvanceSearch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.PSL.management.employeeModel.EmployeeAttendanceEntity;

public class AttendenceDataMapper implements RowMapper<EmployeeAttendanceEntity>{

	@Override
	public EmployeeAttendanceEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmployeeAttendanceEntity employeeAttendenceEntity = new EmployeeAttendanceEntity();
		employeeAttendenceEntity.setId(rs.getLong("id"));
		employeeAttendenceEntity.setEmployeeId(rs.getString("employeeid"));
		employeeAttendenceEntity.setEmployeeName(rs.getString("employeename"));
		employeeAttendenceEntity.setAttendanceDate(rs.getDate("attendencedate"));
		employeeAttendenceEntity.setEntryTime(rs.getDate("entrytime"));
		employeeAttendenceEntity.setExitTime(rs.getDate("exittime"));
		return employeeAttendenceEntity;
	}

}
