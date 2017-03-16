package com.dvsmedeiros.freight.domain;

import com.dvsmedeiros.commons.domain.DomainSpecificEntity;

public class Freight extends DomainSpecificEntity {

	private String serviceName;
	private Double value;
	private String deadline;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

}
