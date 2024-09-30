package com.PSL.management.employeeModelRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.PSL.management.employeeModel.EmployeeAttendanceEntity;

import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeAttendanceDataRepository extends JpaRepository<EmployeeAttendanceEntity, Long>,
        PagingAndSortingRepository<EmployeeAttendanceEntity, Long> {


    String ATTENDANCE_DATA_QUERY = "select * from mraocompany.employee_attendance_data as a where a.employee_id like %?1%\n" +
            " OR a.employee_name like %?1%";
    String ATTENDANCE_CHECK_QUERY ="SELECT CASE WHEN EXISTS (SELECT * FROM mraocompany.employee_attendance_data WHERE attendance_date=:date)\n" +
            "    THEN 'TRUE'\n" +
            "    ELSE 'FALSE'\n" +
            "END";
    @Query(value = ATTENDANCE_DATA_QUERY, nativeQuery = true)
    List<EmployeeAttendanceEntity> getEmployeeAttendanceBySearching(String EmpIdOrName);

    @Query(value = ATTENDANCE_CHECK_QUERY,nativeQuery = true)
    boolean ExistsByAttendanceDate(Date date);

}
