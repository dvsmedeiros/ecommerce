package com.dvsmedeiros.commons.domain;

import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;

import com.dvsmedeiros.bce.domain.DomainEntity;

@SuppressWarnings("serial")
@Entity
public class Role extends DomainEntity implements GrantedAuthority {

	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String getAuthority() {
		return role;
	}

}
