package com.dvsmedeiros.product.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;

@AttributeOverride(name = "value", column = @Column(name = "PACKING_TYPE"))
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
