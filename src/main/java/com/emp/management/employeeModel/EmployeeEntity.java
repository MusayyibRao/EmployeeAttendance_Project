package com.emp.management.employeeModel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.util.Date;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "employeeData")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String employeeId;

    private String employeeFirstName;

    private String employeeLastName;

    private String employeePhoneNumber;

    @Column(unique = true,nullable = false)
    private String email;

    private String employeeAddress;

    @Temporal(TemporalType.DATE)
    private Date employeeRegistrationDate;

    @Temporal(TemporalType.DATE)
    private Date employeeJoiningDate;

    @Temporal(TemporalType.DATE)
    private Date employeeLeaveDate;

    @Lob
    private Blob employeeImage;




}




