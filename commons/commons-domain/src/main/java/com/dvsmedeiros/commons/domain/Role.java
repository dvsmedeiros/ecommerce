package com.dvsmedeiros.commons.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.dvsmedeiros.bce.domain.DomainEntity;

@Entity
@Table( name = "ROLES" )
public class Role extends DomainEntity {

	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
