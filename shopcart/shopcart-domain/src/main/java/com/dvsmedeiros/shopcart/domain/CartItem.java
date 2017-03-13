package com.dvsmedeiros.shopcart.domain;

import com.dvsmedeiros.commons.domain.DomainEntity;
import com.dvsmedeiros.product.domain.Price;
import com.dvsmedeiros.product.domain.Product;

public class CartItem extends DomainEntity {
	
	private static final int MINIMAL_QUANTITY = 1;
	
	private Product product;
	private int quantity;
	private Price salePrice;
	
	public CartItem() {
		this.product = new Product();
		this.salePrice = new Price();
		this.quantity = MINIMAL_QUANTITY;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Price getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Price salePrice) {
		this.salePrice = salePrice;
	}

}
