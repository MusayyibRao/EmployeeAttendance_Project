package com.PSL.management.absentDataAdvanceSearch;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.PSL.management.employeeModel.EmployeeAbsentEntity;

@Repository
public interface AbsentAdvanceSearchDao {
	
	List<EmployeeAbsentEntity> employeeAbsentList(String employeeName, String employeeId, String leave,
                                                  String employeeAbsentDate);

}
