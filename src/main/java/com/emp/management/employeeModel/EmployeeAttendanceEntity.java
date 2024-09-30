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
@Table(name = "employeeAttendanceData")
public class EmployeeAttendanceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String employeeName;
//	@Column(unique = true,nullable = false)
	private String employeeId;
	@Column(unique = true,nullable = false)
	@Temporal(TemporalType.DATE)
	private Date attendanceDate;
	@Temporal(TemporalType.TIME)
	private Date entryTime;
	@Temporal(TemporalType.TIME)
	private Date exitTime;
	private String attendanceType;


	
}
