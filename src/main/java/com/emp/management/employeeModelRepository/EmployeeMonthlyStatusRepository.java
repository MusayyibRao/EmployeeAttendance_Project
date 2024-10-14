package com.emp.management.employeeModelRepository;

import com.emp.management.employeeModel.EmployeeAttendanceEntity;
import com.emp.management.employeeModel.EmployeeMonthlyStatusEntity;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeMonthlyStatusRepository extends JpaRepository<EmployeeMonthlyStatusEntity, Long>,
        PagingAndSortingRepository<EmployeeMonthlyStatusEntity, Long> {

    String MONTHLY_ATTENDANCE_CHECK_QUERY = "SELECT CASE WHEN EXISTS (SELECT * FROM mraocompany.employee_monthly_present_data WHERE employee_id=:empId)\n" +
            "    THEN 'TRUE'\n" +
            "    ELSE 'FALSE'\n" +
            "END";
    String ATTENDANCE_MONTHLY_DATA_QUERY = "select * from mraocompany.employee_monthly_present_data where employee_id=:employeeId and month(month_year) =:month";

    @Query(value = MONTHLY_ATTENDANCE_CHECK_QUERY, nativeQuery = true)
    boolean existsByEmployeeId(String empId);

    @Query(value = ATTENDANCE_MONTHLY_DATA_QUERY, nativeQuery = true)
    Optional<EmployeeMonthlyStatusEntity> findByEmployeeIdAndMonth(String employeeId, String month);


}
