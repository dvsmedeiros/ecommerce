package com.dvsmedeiros.order.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.domain.DomainSpecificEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Component
@Entity
@Table(name = "STATUS_ORDER")
public class StatusOrder extends DomainSpecificEntity {

	public static final String PROCESSING = "PROCESSING";
	public static final String PAYMENT = "PAYMENT";
	public static final String SEPARATION = "SEPARATION";
	public static final String TRANSPORTATION = "TRANSPORTATION";
	public static final String DELIVERY = "DELIVERY";
	public static final String REJECTED = "REJECTED";
	public static final String EXCHANGE = "EXCHANGE";
	public static final String EXCHANGED = "EXCHANGED";
	public static final String APPROVED = "APPROVED";
	public static final String DISAPPROVED = "DISAPPROVED";

	public static final Map<String, List<String>> status = new HashMap<>();

	public StatusOrder() {
		status.put(PROCESSING, Arrays.asList(PROCESSING, PAYMENT));
		status.put(PAYMENT, Arrays.asList(PAYMENT, APPROVED, DISAPPROVED));
		status.put(APPROVED, Arrays.asList(APPROVED, SEPARATION));
		status.put(SEPARATION, Arrays.asList(SEPARATION, TRANSPORTATION));
		status.put(TRANSPORTATION, Arrays.asList(TRANSPORTATION, DELIVERY));
		status.put(DELIVERY, Arrays.asList(DELIVERY));
		status.put(EXCHANGE, Arrays.asList(EXCHANGE, EXCHANGED, REJECTED));
		status.put(EXCHANGED, Arrays.asList(EXCHANGED));
	}

	private List<String> getPossibleStatus(String current) {

		List<String> list = status.get(current);
		if (list != null && !list.isEmpty()) {
			return list;
		}
		return Arrays.asList(current);
	}

	@JsonSerialize
	public List<String> possibleStatus() {
		return getPossibleStatus(this.getCode());
	}

	public void setPossibleStatus(List<String> possibleStatus) {
		return;
	}

}
