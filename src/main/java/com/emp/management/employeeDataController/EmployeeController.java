package com.emp.management.employeeDataController;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.emp.management.request.EmployeeUpdateRequest;
import com.emp.management.response.EmployeeResponse;
import com.emp.management.bulkDataUpload.BulkDataUploadService;
import com.emp.management.bulkDataUpload.RegisterEmployeesHelper;
import com.emp.management.employeeDataAdvanceSearch.EmployeeManageDataAdvanceSearchDao;
import com.emp.management.employeeModel.EmployeeEntity;
import com.emp.management.employeeService.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/employee")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;


    @Autowired
    private BulkDataUploadService bulkDataUploadService;

    @Autowired
    private EmployeeManageDataAdvanceSearchDao employeeManageDataAdvanceSearchDao;

    @Autowired
    ObjectMapper objectMapper;

    /*__________________________________________ADD NEW EMPLOYEE API______________________________________*/

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN')")
    public ResponseEntity<EmployeeResponse> addEmployee(@RequestParam(value = "employeeFirstName", required = false) String employeeFirstName
            , @RequestParam(value = "employeeLastName", required = false) String employeeLastName
            , @RequestParam(value = "employeePhoneNumber", required = false) String employeePhoneNumber
            , @RequestParam(value = "email", required = false) String email
            , @RequestParam(value = "employeeAddress", required = false) String employeeAddress
            , @RequestParam(value = "employeeImage", required = false) MultipartFile employeeImage) throws IOException, SQLException {

        EmployeeResponse response = employeeService.addEmployee(employeeFirstName, employeeLastName, employeePhoneNumber, email, employeeAddress, employeeImage);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    /*__________________________________________GET ALL EMPLOYEE API______________________________________*/

    @GetMapping("/detailsAll")
    @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN')")
    public ResponseEntity<EmployeeResponse> getAllEmployees() {
        EmployeeResponse response = employeeService.getAllEmployees();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    /*__________________________________________GET EMPLOYEE BY ID API______________________________________*/

    @GetMapping("/getEmployeeDataById")
    @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN')")
    public ResponseEntity<EmployeeResponse> getEmployeeDetailsByEmployeeId(@RequestParam(name = "employeeId") String employeeId) {
        EmployeeResponse response = employeeService.getEmployeeDetailsByEmployeeId(employeeId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    /*__________________________________________UPDATE EMPLOYEE API______________________________________*/

    @PutMapping("/updateEmployeeData")
    public ResponseEntity<EmployeeResponse> updateEmployeeDetailsById(@RequestParam("employeeData") String employeeData, @RequestParam(name = "employeeId") String employeeId, @RequestParam("employeeImage") MultipartFile employeeImage) throws JsonProcessingException {
        EmployeeUpdateRequest employeeUpdateRequest = objectMapper.readValue(employeeData, EmployeeUpdateRequest.class);
        EmployeeResponse response = employeeService.updateEmployeeDetails(employeeUpdateRequest, employeeImage, employeeId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    /*__________________________________________DELETE EMPLOYEE API______________________________________*/

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('SUPER_ADMIN') ")
    public ResponseEntity<EmployeeResponse> deleteEmployeeId(@RequestParam(name = "employeeId") String employeeId) {

        EmployeeResponse response = employeeService.deleteEmployee(employeeId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    /*__________________________________________SEARCHING EMPLOYEE API______________________________________*/

    @GetMapping("/search")
    public ResponseEntity<EmployeeResponse> getEmployeeBySearch(@RequestParam("anyDetails") String anyDetails) {
        EmployeeResponse response = employeeService.getEmployeeBySearch(anyDetails);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


    /*__________________________________________FILTER EMPLOYEE API______________________________________*/

    @GetMapping("/filter")
    public List<EmployeeEntity> RegisteredEmployee(@RequestParam(required = false, name = "id") Long id,
                                                   @RequestParam(required = false, name = "employeeid") String employeeid,
                                                   @RequestParam(required = false, name = "employeefirstname") String employeefirstname,
                                                   @RequestParam(required = false, name = "employeelastname") String employeelastname,
                                                   @RequestParam(required = false, name = "employeeusername") String employeeusername,
                                                   @RequestParam(required = false, name = "employeephonenumber") String employeephonenumber,
                                                   @RequestParam(required = false, name = "employeemial") String employeemial,
                                                   @RequestParam(required = false, name = "employeeaddress") String employeeaddress,
                                                   @RequestParam(required = false, name = "employeejoindate") String employeejoindate,
                                                   @RequestParam(required = false, name = "employeeregistrationdate") String employeeregistrationdate,
                                                   @RequestParam(required = false, name = "employeeleavedate") String employeeleavedate) {

        return employeeManageDataAdvanceSearchDao.findRegisteredEmployeeData(employeeid, employeefirstname, employeelastname, employeeusername, employeephonenumber,
                employeemial, employeeaddress, employeejoindate, employeeregistrationdate, employeeleavedate);
    }

    /*__________________________________________UPLOAD FILE EMPLOYEE API______________________________________*/


    @PostMapping("/uploadFile")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam(name = "filename") String filename) {
        if (RegisterEmployeesHelper.checkExcelFormat(file)) {
            this.bulkDataUploadService.registerEmployeesSave(file, filename);
            return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
    }

}
