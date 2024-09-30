package com.emp.management;

import java.util.Date;
import java.util.List;

import com.emp.management.dataModel.AdminEntity;
import com.emp.management.repository.AdminRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
//@CrossOrigin(origins = "http://localhost:4200")
public class MrCompanyEmployeeApplication {

	@Autowired
	private AdminRepository adminRepository;

	Date date = new Date();

	AdminEntity adminEntity = new AdminEntity();

	@PostConstruct
	public void initUsers() {

		boolean value = (adminRepository.findByEmail("musa1234@gmail.com").isPresent());

		if (value) {
			System.out.println("value of admin"+value);
		} else {
			adminEntity.setFirstname("musa");
			adminEntity.setLastname("rao");
			adminEntity.setEmail("musa1234@gmail.com");
			String password = new BCryptPasswordEncoder().encode("123");
			adminEntity.setPassword(password);
			adminEntity.setCreatedDate(date);
			adminEntity.setModifyDate(date);
			adminEntity.setCreatedBy("musa");
			adminEntity.setModifyBy("musa");
			adminEntity.setRole("SUPER_ADMIN");
			List<AdminEntity> list = List.of(adminEntity);
			adminRepository.saveAll(list);
		}
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MrCompanyEmployeeApplication.class, args);
	}

}
