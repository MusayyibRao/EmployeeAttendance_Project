package com.emp.management.controller;

import java.util.*;

import com.emp.management.request.LoginRequest;
import com.emp.management.response.AdminResponse;
import com.emp.management.response.RegisterDto;
import com.emp.management.adminAdvanceSearch.RegisterAdvanceSearchDao;
import com.emp.management.bulkDataUpload.AdminRegisterHelper;
import com.emp.management.bulkDataUpload.BulkDataUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.emp.management.dataModel.AdminEntity;
import com.emp.management.service.AdminService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/admin")
//@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AdminService adminService;

    @Autowired
    private RegisterAdvanceSearchDao registerAdvanceSearchDao;

    @Autowired
    private BulkDataUploadService bulkDataUploadService;

    /*__________________________________________ADD NEW ADMIN API______________________________________*/

    @PostMapping("/newAdmin")
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<AdminResponse> addUser(@RequestBody AdminEntity adminEntity) {
        AdminResponse response = adminService.addUser(adminEntity);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    /*____________________________________________ADMIN LOGIN API________________________________________*/

    @PostMapping("/loginAdmin")
    public ResponseEntity<AdminResponse> persistMessage(@RequestBody LoginRequest loginRequest) {
        AdminResponse response = adminService.login(loginRequest);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    /*_____________________________________________ALL ADMIN DETAILS API____________________________________*/

    @GetMapping("/allAdminDetails")
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<AdminResponse> getUserList() {
        AdminResponse response = adminService.getAllAdmin();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    /*_____________________________________________ADMIN DETAILS BY USERNAME API________________________________*/

    @GetMapping("/AdminDetailsByUsername")
    @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('ADMIN')")
    public ResponseEntity<AdminResponse> getUser(@RequestParam(name = "username") String username) {
        AdminResponse response = adminService.getUser(username);
        return ResponseEntity.status(response.getStatusCode()).body(response);


    }

    /*________________________________________________DELETE ADMIN API______________________________________________*/

    @DeleteMapping("/deleteAdmin")
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<AdminResponse> deleteUser(@RequestParam("email") String email) {
        AdminResponse response = adminService.deleteUser(email);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


    /*_____________________________________________UPDATE ADMIN DETAILS BY USERNAME API________________________________*/
    @PutMapping("/UpdateAdminDetailByUsername")
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<AdminResponse> updateUser(@RequestBody RegisterDto register, @RequestParam(name = "email") String email) {
        AdminResponse response = adminService.updateUser(register, email);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }



    /*____________________________________________ADMIN ADVANCE SEARCH API______________________________________________*/

    @GetMapping("/adminAdvanceSearch")
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public List<AdminEntity> adminDataSearch(@RequestParam(required = false, name = "id") Long id,
                                             @RequestParam(required = false, name = "firstname") String firstname,
                                             @RequestParam(required = false, name = "lastname") String lastname,
                                             @RequestParam(required = false, name = "username") String username,
                                             @RequestParam(required = false, name = "createdBy") String createdBy,
                                             @RequestParam(required = false, name = "modifyBy") String modifyBy) {

        List<AdminEntity> listofAdmin = null;
        List<AdminEntity> adminList = registerAdvanceSearchDao.searchAdminData(id, firstname, lastname, username, createdBy, modifyBy);
        listofAdmin = adminList;

        return listofAdmin;
    }

//	____________________________________________9______________________________________________

    @PostMapping("/adminUploadFile")
    public ResponseEntity<?> adminFile(@RequestParam("file") MultipartFile file,
                                       @RequestParam("filename") String filename) {
        if (AdminRegisterHelper.checkExcelFormat(file)) {
            bulkDataUploadService.adminsave(file, filename);
            return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
    }


}
