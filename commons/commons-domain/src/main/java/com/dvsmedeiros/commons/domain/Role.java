package com.dvsmedeiros.commons.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.domain.DomainSpecificEntity;

@Component
@Entity
@Table( name = "ROLES" )
@SuppressWarnings("serial")
public class Role extends DomainSpecificEntity implements GrantedAuthority {
	
	public static final String USER_ROLE = "USER";
	public static final String ADMIN_ROLE = "USER";
	
	@Override
	public String getAuthority() {
		return getCode();
	}

}
