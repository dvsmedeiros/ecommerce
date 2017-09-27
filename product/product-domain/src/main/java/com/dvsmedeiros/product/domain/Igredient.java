package com.dvsmedeiros.product.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.domain.DomainSpecificEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Component
@Entity
@Table(name = "IGREDIENTS")
public class Igredient extends DomainSpecificEntity {

	@ManyToOne
	@JsonBackReference
	private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
