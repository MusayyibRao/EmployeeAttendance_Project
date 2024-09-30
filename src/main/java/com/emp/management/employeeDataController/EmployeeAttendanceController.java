package com.emp.management.employeeDataController;

import java.util.Map;

import com.emp.management.response.EmployeeResponse;
import com.emp.management.bulkDataUpload.BulkDataUploadService;
import com.emp.management.bulkDataUpload.PresentEmployeeHelper;
import com.emp.management.employeeAttendenceAdvanceSearch.AttendanceAdvanceSearchService;
import com.emp.management.employeeService.EmployeeService;
import com.emp.management.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/attendanceManage")
public class EmployeeAttendanceController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private AttendanceAdvanceSearchService attendanceAdvanceSearchService;

    @Autowired
    private BulkDataUploadService bulkDataUploadService;

    /*__________________________________________ADD EMPLOYEE ATTENDANCE API______________________________________*/

    @PostMapping("/employeeAttendance")
    public ResponseEntity<EmployeeResponse> employeeAttendance(@RequestParam("employeeId") String employeeId, @RequestParam("attendanceType") String attendanceType, @RequestParam("leaveReason") String leaveReason) {
        EmployeeResponse response = employeeService.addEmployeeAttendanceDetails(employeeId,attendanceType,leaveReason);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

   /* @GetMapping("{pageNo}/{pageSize}")
    public List<EmployeeAttendanceEntity> getAttendanceEmployeeData(@PathVariable int pageNo,
                                                                    @PathVariable int pageSize) {

//		List<EmployeeRegistrationEntityModel> allEmployeeList =  employeeService.EmployeeAllEmployeeList();
        return iRegisterService.findEmployeePresentPage(pageNo, pageSize);
    }*/

    /*__________________________________________SEARCHING EMPLOYEE ATTENDANCE API______________________________________*/

    @GetMapping("/attendanceEmployeesSearching")
    public ResponseEntity<EmployeeResponse> employeesAttendance(@RequestParam("empIdOrName") String empIdOrName) {
        EmployeeResponse response = attendanceAdvanceSearchService.employeesAttendanceList(empIdOrName);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/uploadEmployeePresentFile")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam(name = "filename") String filename) {
        if (PresentEmployeeHelper.checkExcelFormat(file)) {
            this.bulkDataUploadService.presentEmployeeSave(file, filename);
            return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
    }
}
