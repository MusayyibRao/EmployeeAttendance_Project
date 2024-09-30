package com.PSL.management.employeeModelRepository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.PSL.management.employeeModel.EmployeeAbsentEntity;

@Repository
public interface EmployeeAbsentRepository
		extends JpaRepository<EmployeeAbsentEntity, Long>{

//	@Query("SELECT u FROM EmployeeAbsentDetails u WHERE u.employeeName=?1")
//	List<EmployeeAbsentEntity> findByUsername(String employeeName);


	String ABSENT_CHECK_QUERY ="SELECT CASE WHEN EXISTS (SELECT * FROM mraocompany.employee_absent_data WHERE emloyee_absent_date=:date)\n" +
			"    THEN 'TRUE'\n" +
			"    ELSE 'FALSE'\n" +
			"END";

	@Query(value = ABSENT_CHECK_QUERY,nativeQuery = true)
	boolean ExistsByAbsentDate(Date date);

}
