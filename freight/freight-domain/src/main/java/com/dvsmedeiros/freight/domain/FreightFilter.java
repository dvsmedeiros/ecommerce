package com.dvsmedeiros.freight.domain;

import java.util.ArrayList;
import java.util.List;

import com.dvsmedeiros.commons.domain.Filter;
import com.dvsmedeiros.shopcart.domain.CartItem;

public class FreightFilter extends Filter<Freight> {

	private List<CartItem> cartItems = new ArrayList<>();

	public FreightFilter(Class<Freight> clazz) {
		super(clazz);
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	
}
