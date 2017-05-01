package com.dvsmedeiros.commons.domain;

import javax.persistence.Embeddable;

import org.springframework.stereotype.Component;

@Component
@Embeddable
public class ExpiringDate {

	private Integer month;
	private Integer year;

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

}
