package com.dvsmedeiros.freight.domain;

import java.math.BigDecimal;
import java.util.List;

import com.dvsmedeiros.product.domain.Product;

public class FreightRequest {

	private Product product;
	private List<Product> products;
	private BigDecimal declaredValue;
	private String postalCodeSource;
	private String postalCodeDestine;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BigDecimal getDeclaredValue() {
		return declaredValue;
	}

	public void setDeclaredValue(BigDecimal declaredValue) {
		this.declaredValue = declaredValue;
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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
