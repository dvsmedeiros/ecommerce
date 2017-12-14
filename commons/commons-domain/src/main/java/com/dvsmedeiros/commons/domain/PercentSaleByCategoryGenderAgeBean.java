package com.dvsmedeiros.commons.domain;

import com.dvsmedeiros.bce.domain.IEntity;

public class PercentSaleByCategoryGenderAgeBean implements IEntity{
	
	private String category;
	private Gender gender;
	private Double averageAge;
	private long quantity;

	public PercentSaleByCategoryGenderAgeBean(Double averageAge, Gender gender, String category, long quantity) {
		this.category = category;
		this.gender = gender;
		this.averageAge = averageAge;
		this.quantity = quantity;
	}

	public String getCategory() {
		return category;
	}

	public double getAverageAge() {
		return averageAge;
	}

	public long getQuantity() {
		return quantity;
	}

	public Gender getGender() {
		return gender;
	}

}
