package com.dvsmedeiros.commons.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.domain.DomainSpecificEntity;

@Component
@Entity
@Table(name = "CREDIT_CARDS")
public class CreditCard extends DomainSpecificEntity {

	private String aNumber;
	private String name;
	private ExpiringDate expiringDate;
	private String cvv;
	@ManyToOne
	private CardFlag flag;
	
	private Boolean principal;
	
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

	public CardFlag getFlag() {
		return flag;
	}

	public void setFlag(CardFlag flag) {
		this.flag = flag;
	}

	public Boolean getPrincipal() {
		return principal;
	}

	public void setPrincipal(Boolean principal) {
		this.principal = principal;
	}
	
	
	
}
