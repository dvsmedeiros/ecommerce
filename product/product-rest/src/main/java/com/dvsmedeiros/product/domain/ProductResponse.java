package com.dvsmedeiros.product.domain;

import com.dvsmedeiros.bce.domain.StatusResponse;

public class ProductResponse extends StatusResponse {
	
	private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
