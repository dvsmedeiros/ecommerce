package com.dvsmedeiros.commons.domain;

import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import com.dvsmedeiros.address.domain.Address;
import com.dvsmedeiros.bce.domain.DomainSpecificEntity;

@MappedSuperclass
public class People extends DomainSpecificEntity {

	@OneToOne
	private Address address;
	private String email;
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
