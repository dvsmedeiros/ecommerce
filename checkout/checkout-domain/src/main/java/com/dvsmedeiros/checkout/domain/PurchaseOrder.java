package com.dvsmedeiros.checkout.domain;

import com.dvsmedeiros.commons.domain.DomainEntity;
import com.dvsmedeiros.freight.domain.Freight;
import com.dvsmedeiros.shopcart.domain.Cart;

public class PurchaseOrder extends DomainEntity {

	private Cart cart;
	private Freight freight;

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Freight getFreight() {
		return freight;
	}

	public void setFreight(Freight freight) {
		this.freight = freight;
	}
}
