package com.dvsmedeiros.commons.domain;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.domain.DomainSpecificEntity;
import com.dvsmedeiros.commons.domain.User;

@Component
@Entity
@Table(name = "CUPONS")
public class Cupom extends DomainSpecificEntity {

	private Calendar expiryDate;
	private Double value;
	@ManyToOne(cascade = CascadeType.DETACH)
	private User owner;
	@Enumerated(EnumType.STRING)
	private CupomType type;
	
	public Cupom() {
	}
	
	public Cupom(String code) {
		this.setCode(code);
	}
	
	public Calendar getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Calendar expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public CupomType getType() {
		return type;
	}

	public void setType(CupomType type) {
		this.type = type;
	}
	
}
