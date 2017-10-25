package com.dvsmedeiros.commons.domain;

import javax.persistence.MappedSuperclass;

import com.dvsmedeiros.bce.domain.DomainEntity;

@MappedSuperclass
public class Item extends DomainEntity {

	public static final int MINIMAL_QUANTITY = 0;

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
