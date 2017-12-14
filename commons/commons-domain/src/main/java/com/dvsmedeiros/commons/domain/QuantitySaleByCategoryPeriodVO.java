package com.dvsmedeiros.commons.domain;

import com.dvsmedeiros.bce.domain.IEntity;

public class QuantitySaleByCategoryPeriodVO implements IEntity {

	private String category;
	private Integer month;
	private Long quantity;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
}