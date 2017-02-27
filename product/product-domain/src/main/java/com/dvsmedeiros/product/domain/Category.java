package com.dvsmedeiros.product.domain;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.commons.domain.DomainSpecificEntity;

@Component
@Entity
public class Category extends DomainSpecificEntity {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
