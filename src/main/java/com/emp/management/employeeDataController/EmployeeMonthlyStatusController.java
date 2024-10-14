package com.emp.management.employeeDataController;

import com.emp.management.employeeModelRepository.EmployeeAbsentRepository;
import com.emp.management.employeeModelRepository.EmployeeMonthlyStatusRepository;
import com.emp.management.employeeService.EmployeeService;
import com.emp.management.response.EmployeeResponse;
import com.emp.management.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/monthlyStatus")
public class EmployeeMonthlyStatusController {


    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeMonthlyStatusRepository employeeMonthlyStatusRepository;

    /*__________________________________________ADD MONTHLY ATTENDANCE API______________________________________*/

    @PostMapping("/add_monthly_attendance")
    public ResponseEntity<EmployeeResponse> addEmployeeAttendanceMonthly(@RequestParam("employeeId") String employeeId, @RequestParam("month") String month) {

        EmployeeResponse response = employeeService.addEmployeeMonthlyAttendanceDetails(employeeId, month);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    /*__________________________________________GET MONTHLY ATTENDANCE BY EMPLOYEE ID API______________________________________*/

    @PostMapping("/get_employee_attendance_data")
    public ResponseEntity<EmployeeResponse> getEmployeeAttendanceMonthlyDataById(@RequestParam("employeeId") String employeeId, @RequestParam("month") String month) {
        EmployeeResponse response = employeeService.getEmployeeMonthlyAttendanceDetails(employeeId, month);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    /*__________________________________________GET ALL MONTHLY ATTENDANCE API______________________________________*/

    @GetMapping("get_all_employee_attendance_data")
    public ResponseEntity<EmployeeResponse> getAllEmployeeAttendanceMonthlyData() {
        EmployeeResponse response = employeeService.getAllEmployeeMonthlyAttendanceDetails();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

	/*@GetMapping("{pageNo}/{pageSize}")
	public List<EmployeeMonthlyStatusEntity> getAllEmployeeMonthlyStatus(@PathVariable int pageNo,
			@PathVariable int pageSize) {
		return iRegisterService.findEmployeeMonthlyStatusPage(pageNo, pageSize);
	}*/

}
