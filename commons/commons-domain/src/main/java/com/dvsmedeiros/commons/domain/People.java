package com.dvsmedeiros.commons.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.dvsmedeiros.address.domain.Address;
import com.dvsmedeiros.bce.domain.DomainSpecificEntity;

@MappedSuperclass
public class People extends DomainSpecificEntity {

	@OneToOne(cascade = CascadeType.PERSIST)
	private Address address;
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Phone> phones;
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

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

}
