package com.emp.management.employeeModelRepository;

import java.util.Date;

import com.emp.management.employeeModel.EmployeeAbsentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeAbsentRepository extends JpaRepository<EmployeeAbsentEntity, Long> {

    String ABSENT_CHECK_QUERY = "SELECT CASE WHEN EXISTS (SELECT * FROM mraocompany.employee_absent_data WHERE emloyee_absent_date=:date And employee_id=:employeeId)\n" +
            "    THEN 'TRUE'\n" +
            "    ELSE 'FALSE'\n" +
            "END";

    @Query(value = ABSENT_CHECK_QUERY, nativeQuery = true)
    boolean ExistsByAbsentDate(Date date, String employeeId);

}
