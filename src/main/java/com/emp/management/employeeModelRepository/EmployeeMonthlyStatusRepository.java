package com.emp.management.employeeModelRepository;

import com.emp.management.employeeModel.EmployeeMonthlyStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeMonthlyStatusRepository extends JpaRepository<EmployeeMonthlyStatusEntity, Long>,
		PagingAndSortingRepository<EmployeeMonthlyStatusEntity, Long> {

}
