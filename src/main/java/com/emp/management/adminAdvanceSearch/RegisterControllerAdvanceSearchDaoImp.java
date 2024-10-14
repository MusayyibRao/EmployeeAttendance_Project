package com.emp.management.adminAdvanceSearch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.emp.management.dataModel.AdminEntity;

@Service
public class RegisterControllerAdvanceSearchDaoImp implements RegisterAdvanceSearchDao {

//	 Implementation Class of  RegisterAdvanceSearchDao

    @Autowired
    private JdbcTemplate jdbcTemplate;

    RegisterDataMapper registerDataMapper = new RegisterDataMapper();

    @Override
    public List<AdminEntity> searchAdminData(Long id, String firstname, String lastname, String username, String createdBy,
                                             String modifyBy) {
        String admin_data_query = "SELECT * FROM admindata WHERE 1 = 1";
        if (id != null) {
            admin_data_query = admin_data_query + " AND id like '%" + id + "%'";
        }
        if (firstname != null) {
            admin_data_query = admin_data_query + " AND firstname like '%" + firstname + "%' ";
        }
        if (lastname != null) {
            admin_data_query = admin_data_query + " AND lastname like '%" + lastname + "%' ";
        }
        if (username != null) {
            admin_data_query = admin_data_query + " AND username like '%" + username + "%' ";
        }
        if (createdBy != null) {
            admin_data_query = admin_data_query + " AND created_by like '%" + createdBy + "%' ";
        }
        if (modifyBy != null) {
            admin_data_query = admin_data_query + " AND modify_by like '%" + modifyBy + "%' ";
        }
        List<AdminEntity> registeredAdminData = jdbcTemplate.query(admin_data_query, registerDataMapper);
        return registeredAdminData;
    }

}
