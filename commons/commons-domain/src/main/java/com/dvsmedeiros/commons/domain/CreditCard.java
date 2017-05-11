package com.dvsmedeiros.commons.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.domain.DomainEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Component
@Entity
@Table(name = "CREDIT_CARD")
public class CreditCard extends DomainEntity {

	private String aNumber;
	private String name;
	private ExpiringDate expiringDate;
	private String cvv;
	@ManyToOne
	@JsonBackReference
	private User user;

	public String getaNumber() {
		return aNumber;
	}

	public void setaNumber(String aNumber) {
		this.aNumber = aNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ExpiringDate getExpiringDate() {
		return expiringDate;
	}

	public void setExpiringDate(ExpiringDate expiringDate) {
		this.expiringDate = expiringDate;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
