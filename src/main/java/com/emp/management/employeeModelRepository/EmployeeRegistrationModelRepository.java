package com.emp.management.employeeModelRepository;

import com.emp.management.employeeModel.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRegistrationModelRepository extends JpaRepository<EmployeeEntity, Long> {

    String EMPLOYEE_DATA_QUERY = "select * from mraocompany.employee_data as a where a.employee_id like %?1%\n" +
            " OR a.email like %?1% OR a.employee_address like %1% OR a.employee_first_name like %1%\n" +
            " OR a.employee_last_name like %1%";

    boolean existsByEmail(String email);

    @Query(value = "select * from mraocompany.employee_data as a where employee_id=:employeeId", nativeQuery = true)
    Optional<EmployeeEntity> findByEmployeeId(String employeeId);

    @Query(value = "delete from mraocompany.employee_data as a where a.employeeId=:employeeId", nativeQuery = true)
    void deleteByEmployeeId(String employeeId);

    @Query(value = "select Max(id) from mraocompany.employee_data", nativeQuery = true)
    Optional<Long> maxId();

    @Query(value = EMPLOYEE_DATA_QUERY, nativeQuery = true)
    List<EmployeeEntity> getEmployeeBySearching(String anyDetails);

}
