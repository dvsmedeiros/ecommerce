package com.dvsmedeiros.freight.domain;

import java.util.List;

public class FreightResponse {

	private List<FreightService> services;

	public List<FreightService> getServices() {
		return services;
	}

	public void setServices(List<FreightService> services) {
		this.services = services;
	}

}
