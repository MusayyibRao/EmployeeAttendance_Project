package com.emp.management.absentDataAdvanceSearch;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.emp.management.employeeModel.EmployeeAbsentEntity;
import org.springframework.jdbc.core.RowMapper;

public class AbsentDataMapper implements RowMapper<EmployeeAbsentEntity> {

	
//  ____________________About_______________________________
//	RowMapper Class of Entity Class -->EmployeeAbsentDetails.
//	Used to map Row from the database.
//	Used in ---> AbsentAdvanceSearchDaoImp Class.
	@Override
	public EmployeeAbsentEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		EmployeeAbsentEntity employeeAbsentEntity = new EmployeeAbsentEntity();
		employeeAbsentEntity.setId(rs.getLong("id"));
		employeeAbsentEntity.setEmployeeId(rs.getString("employeeid"));
		employeeAbsentEntity.setEmployeeName(rs.getString("employeename"));
		employeeAbsentEntity.setEmloyeeAbsentDate(rs.getDate("emloyeeabsentdate"));
		employeeAbsentEntity.setLeaveReason(rs.getString("off_type"));
		return employeeAbsentEntity;
	}

}
