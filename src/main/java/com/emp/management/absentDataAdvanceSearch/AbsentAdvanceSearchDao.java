package com.emp.management.absentDataAdvanceSearch;

import java.util.List;

import com.emp.management.employeeModel.EmployeeAbsentEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsentAdvanceSearchDao {

    List<EmployeeAbsentEntity> employeeAbsentList(String employeeName, String employeeId, String leave,
                                                  String employeeAbsentDate);

}
