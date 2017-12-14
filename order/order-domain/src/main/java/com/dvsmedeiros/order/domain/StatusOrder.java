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
	private static Map<String, List<String>> status = new HashMap<>();

	static {
		status.put(PROCESSING, Arrays.asList(PROCESSING, PAYMENT));
		status.put(PAYMENT, Arrays.asList(PAYMENT, APPROVED, DISAPPROVED));
		status.put(APPROVED, Arrays.asList(APPROVED, SEPARATION));
		status.put(SEPARATION, Arrays.asList(SEPARATION, TRANSPORTATION));
		status.put(TRANSPORTATION, Arrays.asList(TRANSPORTATION, DELIVERY));
		status.put(DELIVERY, Arrays.asList(DELIVERY));
		status.put(EXCHANGE, Arrays.asList(EXCHANGE, EXCHANGED, REJECTED));
		status.put(EXCHANGED, Arrays.asList(EXCHANGED));
	}

	public StatusOrder(String code, String description) {
		this.setCode(code);
		this.setDescription(description);
	}

	public StatusOrder() {
	}

	@JsonSerialize
	public List<String> possibleStatus() {
		List<String> list = status.get(this.getCode());
		if (list != null && !list.isEmpty()) {
			return list;
		}
		return Arrays.asList(this.getCode());
	}

	public void setPossibleStatus(List<String> possibleStatus) {
		return;
	}

}
