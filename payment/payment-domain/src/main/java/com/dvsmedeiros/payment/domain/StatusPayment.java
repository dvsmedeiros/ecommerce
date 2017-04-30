package com.dvsmedeiros.payment.domain;

public enum StatusPayment {

	APPROVED(1), REFUSED(2), RECEIVED(3);

	private int value;

	private StatusPayment(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
