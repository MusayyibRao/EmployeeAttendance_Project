package com.emp.management.employeeDataAdvanceSearch;

import java.util.List;

import com.emp.management.employeeModel.EmployeeEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeManageDataAdvanceSearchDao {

	List<EmployeeEntity> findRegisteredEmployeeData(String employeeid,
                                                    String employeefirstname, String employeelastname, String employeeusername, String employeephonenumber,
                                                    String employeemial, String employeeaddress, String employeejoindate, String employeeregistrationdate, String employeeleavedate);

}
