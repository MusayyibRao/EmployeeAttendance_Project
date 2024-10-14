package com.emp.management.absentDataAdvanceSearch;

import java.util.List;

import com.emp.management.employeeModel.EmployeeAbsentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class AbsentAdvanceSearchDaoImp implements AbsentAdvanceSearchDao {

//	________________________About_______________________________

//	DAO implementation Class. --> Absent Employees Data Search From the database based on condition.
//	Entity Class -->EmployeeAbsentDetails
//	RowMapper Implementation Class --> AbsentDataMapper


    @Autowired
    private JdbcTemplate jdbcTemplate;

    AbsentDataMapper absentDataMapper = new AbsentDataMapper();

    @Override
    public List<EmployeeAbsentEntity> employeeAbsentList(String employeename, String employeeid, String leave,
                                                         String emloyeeabsentdate) {

        String absent_employee_query = "SELECT * FROM employeeabsentdata WHERE 1 = 1";
        if (employeename != null) {
            absent_employee_query = absent_employee_query + " AND employeename like '%" + employeename + "%'";
        }
        if (employeeid != null) {
            absent_employee_query = absent_employee_query + " AND employeeid like '%" + employeeid + "%'";
        }
        if (leave != null) {
            absent_employee_query = absent_employee_query + " AND off_type like '%" + leave + "%'";
        }
        if (emloyeeabsentdate != null) {
            absent_employee_query = absent_employee_query + " AND emloyeeabsentdate like '%" + emloyeeabsentdate + "%'";
        }

        List<EmployeeAbsentEntity> absent_employee_list = jdbcTemplate.query(absent_employee_query, absentDataMapper);
        return absent_employee_list;
    }

}
