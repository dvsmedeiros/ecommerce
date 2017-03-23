package com.dvsmedeiros.freight.domain;

import com.dvsmedeiros.product.domain.Product;

public class FreightRequest {

	private Product product;
	private String postalCodeSource;
	private String postalCodeDestine;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getPostalCodeSource() {
		return postalCodeSource;
	}

	public void setPostalCodeSource(String postalCodeSource) {
		this.postalCodeSource = postalCodeSource;
	}

	public String getPostalCodeDestine() {
		return postalCodeDestine;
	}

	public void setPostalCodeDestine(String postalCodeDestine) {
		this.postalCodeDestine = postalCodeDestine;
	}
}
