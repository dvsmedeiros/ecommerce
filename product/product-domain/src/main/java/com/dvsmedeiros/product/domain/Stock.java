package com.dvsmedeiros.product.domain;

import javax.persistence.OneToOne;

import com.dvsmedeiros.bce.domain.DomainEntity;

public class Stock extends DomainEntity {
	
	@OneToOne
	private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
