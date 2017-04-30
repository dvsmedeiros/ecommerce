package com.dvsmedeiros.order.domain;

import java.util.List;

import com.dvsmedeiros.bce.domain.DomainSpecificEntity;
import com.dvsmedeiros.commons.domain.User;
import com.dvsmedeiros.freight.domain.Freight;
import com.dvsmedeiros.payment.domain.Payment;

public class Order extends DomainSpecificEntity {

	private Payment payment;
	private StatusOrder statusOrder;
	private List<OrderItem> items;
	private Freight freight;
	private User user;
	
	public Freight getFreight() {
		return freight;
	}

	public void setFreight(Freight freight) {
		this.freight = freight;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public StatusOrder getStatusOrder() {
		return statusOrder;
	}

	public void setStatusOrder(StatusOrder statusOrder) {
		this.statusOrder = statusOrder;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
