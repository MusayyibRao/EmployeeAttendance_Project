package com.emp.management.employeeDataController;

import java.util.List;
import java.util.Map;

import com.emp.management.bulkDataUpload.AbsentEmployeeHelper;
import com.emp.management.bulkDataUpload.BulkDataUploadService;
import com.emp.management.employeeModel.EmployeeAbsentEntity;
import com.emp.management.employeeService.EmployeeService;
import com.emp.management.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.emp.management.absentDataAdvanceSearch.AbsentAdvanceSearchDao;

@RestController
@RequestMapping("/absentManage")
public class EmployeeAbsentController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private AdminService adminService;

	@Autowired
	private AbsentAdvanceSearchDao absentAdvanceSearchDao;

	@Autowired
	private BulkDataUploadService bulkDataUploadService;

//	_____________________________________1___________________________________________

	@PostMapping("/EmployeeAbsent")
	public EmployeeAbsentEntity employeeAbsent(@RequestBody EmployeeAbsentEntity employeeAbsentEntity) {
		EmployeeAbsentEntity employeeAbsent = employeeService.addEmployeeAbsentDetails(employeeAbsentEntity);
		return employeeAbsent;
	}

//	_____________________________________2___________________________________________

//	@GetMapping("{pageNo}/{pageSize}")
//	public List<EmployeeAbsentDetails> getAllEmployee(@PathVariable int pageNo, @PathVariable int pageSize) {
//		return iRegisterService.findEmployeeAbsentPage(pageNo, pageSize);
//	}

//	_____________________________________3___________________________________________
	
	@GetMapping("/absentEmployees")
	public List<EmployeeAbsentEntity> absentEmployee(
			@RequestParam(required = false, name = "employeeid") String employeeid,
			@RequestParam(required = false, name = "employeename") String employeename,
			@RequestParam(required = false, name = "leave") String leave,
			@RequestParam(required = false, name = "emloyeeabsentdate") String emloyeeabsentdate) {
		return absentAdvanceSearchDao.employeeAbsentList(employeeid, employeename, leave, emloyeeabsentdate);
	}

	
//	_____________________________________4___________________________________________
	
	@PostMapping("/uploadEmployeeAbsentFile")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam(name = "filename") String filename) {
		if (AbsentEmployeeHelper.checkExcelFormat(file)) {
			this.bulkDataUploadService.absentEmployeeSave(file, filename);
			return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
	}
}
