package com.dvsmedeiros.payment.domain;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

import org.springframework.stereotype.Component;

@Component
@Embeddable
public class Quota {

	private int number;
	private BigDecimal quotaValue;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public BigDecimal getQuotaValue() {
		return quotaValue;
	}

	public void setQuotaValue(BigDecimal quotaValue) {
		this.quotaValue = quotaValue;
	}

}
