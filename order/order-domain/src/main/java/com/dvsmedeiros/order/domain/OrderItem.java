package com.dvsmedeiros.order.domain;

import java.math.BigDecimal;

import com.dvsmedeiros.commons.domain.Item;
import com.dvsmedeiros.product.domain.Product;

public class OrderItem extends Item {
	
	private Product product;
	private BigDecimal salePrice;
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	
	
}
