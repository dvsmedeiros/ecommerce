package com.dvsmedeiros.shopcart.domain;

import com.dvsmedeiros.commons.domain.Item;
import com.dvsmedeiros.product.domain.Product;

public class CartItem extends Item {
	
	private Product product;
	
	public CartItem() {
		this.product = new Product();
	}
	
	public void moreOneProduct() {
		this.quantity++;
	}
	
	public void lessOneProduct() {
		this.quantity--;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
