package com.dvsmedeiros.order.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.address.domain.Address;
import com.dvsmedeiros.bce.domain.DomainSpecificEntity;
import com.dvsmedeiros.commons.domain.User;
import com.dvsmedeiros.freight.domain.FreightService;
import com.dvsmedeiros.payment.domain.Payment;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Component
@Entity
@Table(name = "ORDERS")
public class Order extends DomainSpecificEntity {

	private BigDecimal total;
	private BigDecimal subTotal;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.DETACH })
	private FreightService freight;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
	private Payment payment;

	@ManyToOne(cascade = CascadeType.DETACH)
	private Cupom cupom;
	
	
	@ManyToOne(cascade = CascadeType.DETACH)
	private Address deliveryAddress;

	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH })
	@JsonManagedReference
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "ORDER_ID")
	private List<OrderItem> items;

	@ManyToOne(cascade = CascadeType.DETACH)
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

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	
	public Cupom getCupom() {
		return cupom;
	}

	public void setCupom(Cupom cupom) {
		this.cupom = cupom;
	}
	
}
