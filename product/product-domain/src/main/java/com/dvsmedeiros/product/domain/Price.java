package com.dvsmedeiros.product.domain;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

import org.springframework.stereotype.Component;

@Component
@Embeddable
public class Price {
	
	private BigDecimal value = BigDecimal.ZERO;
	
	public Price() {
	}
	
	public Price(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

}
