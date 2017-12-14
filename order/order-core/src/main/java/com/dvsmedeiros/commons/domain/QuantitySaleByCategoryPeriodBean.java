package com.dvsmedeiros.commons.domain;

import com.dvsmedeiros.bce.domain.IEntity;

public class QuantitySaleByCategoryPeriodBean implements IEntity {

	private int month;
	private String category;
	private long quantity;

	public QuantitySaleByCategoryPeriodBean(int month, String category, long quantity) {
		this.month = month;
		this.category = category;
		this.quantity = quantity;
	}

	public int getMonth() {
		return month;
	}

	public String getCategory() {
		return category;
	}

	public long getQuantity() {
		return quantity;
	}

}
