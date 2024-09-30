package com.PSL.management.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.PSL.management.dataModel.AdminEntity;

@SuppressWarnings("serial")
public class UserDetailsImpl implements UserDetails {

	private AdminEntity adminEntity;

	public UserDetailsImpl(AdminEntity adminEntity) {
		this.adminEntity = adminEntity;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return adminEntity.getPassword();
	}

	@Override
	public String getUsername() {
		return adminEntity.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
