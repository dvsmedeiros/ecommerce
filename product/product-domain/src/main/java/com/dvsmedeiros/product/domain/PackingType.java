package com.dvsmedeiros.product.domain;

public enum PackingType {

	BOX(1), ROLL(2), ENVELOPE(3);

	private int value;

	private PackingType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
