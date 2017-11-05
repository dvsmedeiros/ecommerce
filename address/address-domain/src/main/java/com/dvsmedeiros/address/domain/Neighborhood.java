package com.dvsmedeiros.address.domain;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

@Component
@Embeddable
public class Neighborhood {
	
	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.PERSIST })
	private City city;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}
