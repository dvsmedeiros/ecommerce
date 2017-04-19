package com.dvsmedeiros.commons.domain;

import com.dvsmedeiros.bce.domain.DomainEntity;

public abstract class Document extends DomainEntity {

	protected String number;
	protected String mask;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getMask() {
		return mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

}
