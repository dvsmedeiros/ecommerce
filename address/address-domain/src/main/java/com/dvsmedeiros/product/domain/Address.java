package com.dvsmedeiros.product.domain;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.domain.DomainEntity;

@Component
public class Address extends DomainEntity {

	private String complemnt;
	private String number;
	private String zipCode;
	private String street;
	private Neighborhood neighborhood;

	public String getComplemnt() {
		return complemnt;
	}

	public void setComplemnt(String complemnt) {
		this.complemnt = complemnt;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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
