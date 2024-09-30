package com.PSL.management.adminAdvanceSearch;


import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.PSL.management.dataModel.AdminEntity;


public class RegisterDataMapper implements RowMapper<AdminEntity>{

	@Override
	public AdminEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		AdminEntity adminEntity = new AdminEntity();
		adminEntity.setId(rs.getLong("id"));
		adminEntity.setFirstname(rs.getString("firstname"));
		adminEntity.setLastname(rs.getString("lastname"));
		adminEntity.setEmail(rs.getString("email"));
		adminEntity.setPassword(rs.getString("password"));
		adminEntity.setCreatedBy(rs.getString("created_by"));
		adminEntity.setModifyBy(rs.getString("modify_by"));
		adminEntity.setCreatedDate(rs.getDate("created_date"));
		adminEntity.setModifyDate(rs.getDate("modify_date"));
		return adminEntity;
	}

}
