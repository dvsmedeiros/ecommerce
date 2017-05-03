package com.dvsmedeiros.payment.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.stereotype.Component;

@Component
@Embeddable
public class Quota {

	@Column(name = "QUOTAS")
	private int aNumber;
	private BigDecimal quotaValue;

	public int getaNumber() {
		return aNumber;
	}

	public void setaNumber(int aNumber) {
		this.aNumber = aNumber;
	}

	public BigDecimal getQuotaValue() {
		return quotaValue;
	}

	public void setQuotaValue(BigDecimal quotaValue) {
		this.quotaValue = quotaValue;
	}

}
