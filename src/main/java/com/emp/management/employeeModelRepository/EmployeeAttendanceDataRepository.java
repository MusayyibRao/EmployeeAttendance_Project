package com.emp.management.employeeModelRepository;

import com.emp.management.employeeModel.EmployeeAttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeAttendanceDataRepository extends JpaRepository<EmployeeAttendanceEntity, Long>,
        PagingAndSortingRepository<EmployeeAttendanceEntity, Long> {


    String ATTENDANCE_DATA_QUERY = "select * from mraocompany.employee_attendance_data as a where a.employee_id like %?1%\n" +
            " OR a.employee_name like %?1%";
    String ATTENDANCE_CHECK_QUERY ="SELECT CASE WHEN EXISTS (SELECT * FROM mraocompany.employee_attendance_data WHERE attendance_date=:date And employee_id=:employeeId)\n" +
            "    THEN 'TRUE'\n" +
            "    ELSE 'FALSE'\n" +
            "END";

    String ATTENDANCE_DATA_QUERY_BY_DATE="SELECT * FROM mraocompany.employee_attendance_data WHERE attendance_date=:date And employee_id=:employeeId";

    String ATTENDANCE_EXIT_TIME_CHECK_QUERY ="SELECT exit_time from mraocompany.employee_attendance_data WHERE attendance_date=:date And employee_id=:employeeId";

    @Query(value = ATTENDANCE_DATA_QUERY, nativeQuery = true)
    List<EmployeeAttendanceEntity> getEmployeeAttendanceBySearching(String EmpIdOrName);

    @Query(value = ATTENDANCE_CHECK_QUERY,nativeQuery = true)
    boolean existsByAttendanceDate(Date date, String employeeId);

    @Query(value = ATTENDANCE_DATA_QUERY_BY_DATE,nativeQuery = true)
    Optional<EmployeeAttendanceEntity> getEmployeeDataByDate(Date date, String employeeId);

    @Query(value = ATTENDANCE_EXIT_TIME_CHECK_QUERY,nativeQuery = true)
    String existsByAttendanceExitTime(Date date,String employeeId);

}
