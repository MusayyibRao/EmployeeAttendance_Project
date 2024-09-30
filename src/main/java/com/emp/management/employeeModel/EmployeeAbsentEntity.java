package com.emp.management.employeeModel;

import java.util.Date;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "employeeAbsentData")
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeAbsentEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String employeeName;
	@Column(unique = true,nullable = false)
	private String employeeId;
	
	@Column(name = "leaveReason")
	private String leaveReason;

	@Temporal(TemporalType.DATE)
	private Date emloyeeAbsentDate;


}
