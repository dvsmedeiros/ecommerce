package com.dvsmedeiros.order.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.domain.DomainEntity;
import com.dvsmedeiros.commons.domain.User;
import com.dvsmedeiros.freight.domain.FreightService;
import com.dvsmedeiros.payment.domain.Payment;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Component
@Entity
@Table(name = "ORDERS")
public class Order extends DomainEntity {

	private BigDecimal total;
	private BigDecimal subTotal;

	@ManyToOne(cascade = CascadeType.ALL)
	private FreightService freight;

	@ManyToOne(cascade = CascadeType.ALL)
	private Payment payment;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ORDER_ID")
	@JsonManagedReference
	private List<OrderItem> items;

	@Enumerated(EnumType.STRING)
	private StatusOrder statusOrder;

	@ManyToOne(cascade = CascadeType.DETACH)
	private User user;

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public StatusOrder getStatusOrder() {
		return statusOrder;
	}

	public void setStatusOrder(StatusOrder statusOrder) {
		this.statusOrder = statusOrder;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public FreightService getFreight() {
		return freight;
	}

	public void setFreight(FreightService freight) {
		this.freight = freight;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

}
