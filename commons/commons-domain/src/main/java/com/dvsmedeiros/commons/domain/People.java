package com.dvsmedeiros.commons.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.dvsmedeiros.address.domain.Address;
import com.dvsmedeiros.bce.domain.DomainSpecificEntity;

@MappedSuperclass
public class People extends DomainSpecificEntity {

	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Address> addresses;

	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Phone> phones;
	private String email;

	public Address getBillingAddress() {
		if (addresses != null && !addresses.isEmpty()) {
			for (Address address : addresses) {
				if (address.getBilling()) {
					return address;
				}
			}
		}
		return null;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
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
