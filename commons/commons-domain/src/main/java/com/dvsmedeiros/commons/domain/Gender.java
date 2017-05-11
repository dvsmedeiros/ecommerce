package com.dvsmedeiros.commons.domain;

public enum Gender {

	MALE(1), FEMALE(2), OTHER(3);

	private int value;

	private Gender(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
