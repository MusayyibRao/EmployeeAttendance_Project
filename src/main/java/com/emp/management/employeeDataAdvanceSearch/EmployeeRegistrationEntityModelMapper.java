package com.emp.management.employeeDataAdvanceSearch;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.emp.management.employeeModel.EmployeeEntity;
import org.springframework.jdbc.core.RowMapper;

public class EmployeeRegistrationEntityModelMapper implements RowMapper<EmployeeEntity> {

	@Override
	public EmployeeEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		EmployeeEntity employeeEntity = new EmployeeEntity();
		
		employeeEntity.setEmployeeId(rs.getString("employeeid"));
		employeeEntity.setEmployeeFirstName(rs.getString("employeefirstname"));
		employeeEntity.setEmployeeLastName(rs.getString("employeelastname"));
		employeeEntity.setEmployeeAddress(rs.getString("employeeaddress"));
		employeeEntity.setEmail(rs.getString("email"));
		employeeEntity.setEmployeeJoiningDate(rs.getDate("employeejoindate"));
		employeeEntity.setEmployeeLeaveDate(rs.getDate("employeeleavedate"));
		employeeEntity.setEmployeePhoneNumber(rs.getString("employeephonenumber"));
		employeeEntity.setEmployeeRegistrationDate(rs.getDate("employeeregistrationdate"));
		return employeeEntity;
	}
}
