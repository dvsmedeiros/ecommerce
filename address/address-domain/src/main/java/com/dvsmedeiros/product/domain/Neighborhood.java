package com.dvsmedeiros.product.domain;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.domain.DomainSpecificEntity;

@Component
public class Neighborhood extends DomainSpecificEntity {

	private City city;

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}
