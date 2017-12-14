package com.dvsmedeiros.commons.domain;

import java.util.List;

import com.dvsmedeiros.bce.domain.IEntity;

public class AnalyzeVO implements IEntity {

	private List<PercentSaleByCategoryGenderAgeVO> first;
	private List<QuantitySaleByCategoryPeriodVO> second;

	public AnalyzeVO(List<PercentSaleByCategoryGenderAgeVO> first, List<QuantitySaleByCategoryPeriodVO> second) {
		this.first = first;
		this.second = second;
	}

	public List<PercentSaleByCategoryGenderAgeVO> getFirst() {
		return first;
	}

	public void setFirst(List<PercentSaleByCategoryGenderAgeVO> first) {
		this.first = first;
	}

	public List<QuantitySaleByCategoryPeriodVO> getSecond() {
		return second;
	}

	public void setSecond(List<QuantitySaleByCategoryPeriodVO> second) {
		this.second = second;
	}

}
