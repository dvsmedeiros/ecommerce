package com.dvsmedeiros.address.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.domain.DomainSpecificEntity;

@Component
@Entity
@Table(name = "ADRRESSES")
public class Address extends DomainSpecificEntity {
	
	private static final String ZIPCODE_SEPARATOR = "-";
	
	@Embedded
	private Neighborhood neighborhood;
	private String complement;
	private String aNumber;
	private String zipCode;
	private String street;
	@ManyToOne	
	private AddressType type;
	private Boolean delivery;
	private Boolean billing;
	private Boolean home;
	
	
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
		this.zipCode = zipCode.trim().replace(ZIPCODE_SEPARATOR, "");
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

	public AddressType getType() {
		return type;
	}

	public void setType(AddressType type) {
		this.type = type;
	}

	public Boolean getDelivery() {
		return delivery;
	}

	public void setDelivery(Boolean delivery) {
		this.delivery = delivery;
	}

	public Boolean getBilling() {
		if(billing == null) {
			return Boolean.FALSE;
		}
		return billing;
	}

	public void setBilling(Boolean billing) {
		this.billing = billing;
	}

	public Boolean getHome() {
		return home;
	}

	public void setHome(Boolean home) {
		this.home = home;
	}
	
}
