package com.PSL.management.employeeDataController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PSL.management.employeeModelRepository.EmployeeAbsentRepository;
import com.PSL.management.employeeModelRepository.EmployeeMonthlyStatusRepository;
import com.PSL.management.employeeService.EmployeeService;
import com.PSL.management.service.AdminService;

@RestController
@RequestMapping("/monthlyStatus")
public class EmployeeMonthlyStatusController {

	private int leavecount = 0;
	private int absentcount = 0;
	private int wfhcount = 0;
	
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeAbsentRepository absentRepository;

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private EmployeeMonthlyStatusRepository employeeMonthlyStatusRepository;

	/*@PostMapping("/AttendenceStatus")
	public EmployeeMonthlyStatusEntity addEmployeeAttendenceStatus(
			@RequestBody EmployeeMonthlyStatusEntity employeeMonthlyEntityModel) {
		employeeService.addEmployeeMonthlyAttendenceDetails(employeeMonthlyEntityModel);
		String username = employeeMonthlyEntityModel.getEmployeeusername();
		List<EmployeeAbsentDetails> user = absentRepository.findByUsername(username);

		user.forEach(e -> {
			if (e.getLeaveReason().toUpperCase().equals("WFH")) {
				wfhcount++;
			} else if (e.getLeaveReason().toUpperCase().equals("ABSENT")) {
				absentcount++;
			} else if (e.getLeaveReason().toUpperCase().equals("LEAVE")) {
				leavecount++;
			}
		});
		int totalLeave = leavecount + absentcount;
		String workdays = employeeMonthlyEntityModel.getTotalworkingdays();
		int workingDays = Integer.parseInt(workdays);
		int totalworkingDays = workingDays - totalLeave;
		int presentdays = totalworkingDays;
		String totalpresentdays = String.valueOf(presentdays);
		String leavedays = String.valueOf(leavecount);
		String absentdays = String.valueOf(absentcount);
		String wfhdays = String.valueOf(wfhcount);
		employeeMonthlyEntityModel.setPresentnumber(totalpresentdays);
		employeeMonthlyEntityModel.setAbsentnumber(absentdays);
		employeeMonthlyEntityModel.setLeavenumber(leavedays);
		employeeMonthlyEntityModel.setWfhnumber(wfhdays);
		employeeMonthlyStatusRepository.save(employeeMonthlyEntityModel);
		return employeeMonthlyEntityModel;
	}

	@GetMapping("{pageNo}/{pageSize}")
	public List<EmployeeMonthlyStatusEntity> getAllEmployeeMonthlyStatus(@PathVariable int pageNo,
			@PathVariable int pageSize) {
		return iRegisterService.findEmployeeMonthlyStatusPage(pageNo, pageSize);
	}*/

}
