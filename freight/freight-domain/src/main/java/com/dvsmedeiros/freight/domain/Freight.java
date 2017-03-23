package com.dvsmedeiros.freight.domain;

import com.dvsmedeiros.commons.domain.DomainSpecificEntity;
import com.dvsmedeiros.product.domain.Product;

public class Freight extends DomainSpecificEntity {

	private Product product;
	private String postalCodeDestine;
	private FreightService service;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getPostalCodeDestine() {
		return postalCodeDestine;
	}

	public void setPostalCodeDestine(String postalCodeDestine) {
		this.postalCodeDestine = postalCodeDestine;
	}

	public FreightService getService() {
		return service;
	}

	public void setService(FreightService service) {
		this.service = service;
	}

}
