package com.dvsmedeiros.address.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.domain.DomainEntity;

@Component
@Entity
public class Address extends DomainEntity {

	@Embedded
	private Neighborhood neighborhood;
	private String complement;
	private String aNumber;
	private String zipCode;
	private String street;
	
	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getaNumber() {
		return aNumber;
	}

	public void setaNumber(String aNumber) {
		this.aNumber = aNumber;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Neighborhood getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(Neighborhood neighborhood) {
		this.neighborhood = neighborhood;
	}

}
