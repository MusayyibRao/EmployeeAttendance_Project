package com.emp.management.employeeModel;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employeeMonthlyPresentData")
public class EmployeeMonthlyStatusEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String employeeId;
    private String leaveNumber;
    private String absentNumber;
    private String wfhNumber;
    private String presentNumber;
    private String totalWorkingDays;
    private Date monthYear;

}
