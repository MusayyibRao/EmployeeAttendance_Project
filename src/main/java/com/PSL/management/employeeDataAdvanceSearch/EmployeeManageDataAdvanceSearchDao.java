package com.PSL.management.employeeDataAdvanceSearch;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.PSL.management.employeeModel.EmployeeEntity;

@Repository
public interface EmployeeManageDataAdvanceSearchDao {

	List<EmployeeEntity> findRegisteredEmployeeData(String employeeid,
                                                    String employeefirstname, String employeelastname, String employeeusername, String employeephonenumber,
                                                    String employeemial, String employeeaddress, String employeejoindate, String employeeregistrationdate, String employeeleavedate);

}