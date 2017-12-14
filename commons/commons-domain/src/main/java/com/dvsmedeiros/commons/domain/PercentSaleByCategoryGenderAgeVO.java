package com.dvsmedeiros.commons.domain;

import com.dvsmedeiros.bce.domain.IEntity;

public class PercentSaleByCategoryGenderAgeVO implements IEntity {
	private String category;
	private Double female = 0D;
	private Double male = 0D;
	private Double other = 0D;
	private Double averageAge = 0D;
	private Double percentFemale = 0D;
	private Double percentMale = 0D;
	private Double percentOther = 0D;
	private String start;
	private String end;
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Double getFemale() {
		return female;
	}

	public void setFemale(Double female) {
		this.female = female;
	}

	public Double getMale() {
		return male;
	}

	public void setMale(Double male) {
		this.male = male;
	}

	public Double getAverageAge() {
		return averageAge;
	}

	public void setAverageAge(Double averageAge) {
		this.averageAge = averageAge;
	}

	public Double getPercentFemale() {
		Double total = female + male + other;
		return female / total * 100;
	}

	public Double getPercentMale() {
		Double total = female + male + other;
		return male / total * 100;
	}

	public Double getPercentOther() {
		Double total = female + male + other;
		return other / total * 100;
	}

	public void setOther(Double other) {
		this.other = other;
	}

	public Double getOther() {
		return other;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}
	
	
}