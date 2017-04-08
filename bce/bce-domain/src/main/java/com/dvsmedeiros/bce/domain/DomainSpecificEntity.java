package com.dvsmedeiros.bce.domain;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class DomainSpecificEntity extends DomainEntity {

	private String code;
	private Boolean active;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
