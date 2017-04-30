package com.dvsmedeiros.commons.domain;

import com.dvsmedeiros.bce.domain.DomainEntity;

public class Item extends DomainEntity {

	private static final int MINIMAL_QUANTITY = 1;

	protected int quantity;

	public Item() {
		this.quantity = MINIMAL_QUANTITY;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
