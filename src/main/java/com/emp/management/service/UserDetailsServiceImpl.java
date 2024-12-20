package com.emp.management.service;

import com.emp.management.dataModel.AdminEntity;
import com.emp.management.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public abstract class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdminRepository dao;

	public UserDetails loadUserByUsername(Long username) throws UsernameNotFoundException {
		AdminEntity adminEntity = dao.findById(username).orElse(null);
		UserDetails details=null;
		if(adminEntity ==null) {
			details = new UserDetailsImpl(adminEntity);
		}
		else
			throw new UsernameNotFoundException("User with " + username + "does not exits...");
		System.out.println(adminEntity);
		return details;
	}
}
