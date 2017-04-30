package com.dvsmedeiros.payment.domain;

public enum PaymentType {
	
	BOLETO(1), CREDIT_CARD(2);
	
	private int value;
	
	private PaymentType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
