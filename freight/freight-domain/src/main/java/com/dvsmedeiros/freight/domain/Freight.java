package com.dvsmedeiros.freight.domain;

import com.dvsmedeiros.commons.domain.DomainSpecificEntity;

public class Freight extends DomainSpecificEntity {

	private FreightRequest request;
	private FreightResponse response;
	
	public Freight() {
		this.request = new FreightRequest();
		this.response = new FreightResponse();
	}
	
	public FreightRequest getRequest() {
		return request;
	}

	public void setRequest(FreightRequest request) {
		this.request = request;
	}

	public FreightResponse getResponse() {
		return response;
	}

	public void setResponse(FreightResponse response) {
		this.response = response;
	}

}
