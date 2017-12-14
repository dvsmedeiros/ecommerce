package com.dvsmedeiros.order.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.address.domain.Address;
import com.dvsmedeiros.bce.domain.DomainSpecificEntity;
import com.dvsmedeiros.commons.domain.Client;
import com.dvsmedeiros.commons.domain.Cupom;
import com.dvsmedeiros.commons.domain.CupomType;
import com.dvsmedeiros.commons.domain.User;
import com.dvsmedeiros.freight.domain.FreightService;
import com.dvsmedeiros.payment.domain.Payment;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Component
@Entity
@Table(name = "ORDERS")
public class Order extends DomainSpecificEntity {

	private BigDecimal total = BigDecimal.ZERO;
	private BigDecimal subTotal = BigDecimal.ZERO;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.DETACH })
	private FreightService freight;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
	private Payment payment;

	@ManyToMany(cascade = { CascadeType.DETACH })
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Cupom> cupons;
	
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
	private Client custumer;
	
	@Formula(value = "extract(month from insertion_date)")
	private int month;
	
	@Formula(value = "extract(year from insertion_date)")
	private int year;
	
	public Order() {
	}
	
	public Order(Long id) {
		this.setId(id);
	}
	
	public BigDecimal getTotal() {
		total.subtract(getTotalCupons());
		total.add(subTotal);
		if(total.doubleValue() < 0) return BigDecimal.ZERO;
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
	
	public Client getCustumer() {
		return custumer;
	}

	public void setCustumer(Client custumer) {
		this.custumer = custumer;
	}

	public FreightService getFreight() {
		return freight;
	}

	public void setFreight(FreightService freight) {
		this.freight = freight;
	}

	public BigDecimal getSubTotal() {
		 BigDecimal calculated = BigDecimal.ZERO;
		if(items != null && !items.isEmpty()) {			
			for (OrderItem item : this.items) {
				calculated = calculated
						.add(item.getProduct().getCalculatedSalePrice().getValue().multiply(BigDecimal.valueOf(item.getQuantity())));
			}
			return calculated;
		}
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

	public List<Cupom> getCupons() {
		return cupons;
	}

	public void setCupons(List<Cupom> cupons) {
		this.cupons = cupons;
	}
	
	public BigDecimal getTotalCupons() {
		double calculated = 0;
		if(this.cupons != null && !this.cupons.isEmpty()) {
			for(Cupom cupom : this.cupons) {
				calculated += cupom.getValue();
			}
		}
		return new BigDecimal(calculated);
	}
	
	public BigDecimal getTotalExchangeCupons() {
		double calculated = 0;
		if(this.cupons != null && !this.cupons.isEmpty()) {
			for(Cupom cupom : this.cupons) {
				if(cupom.getType().equals(CupomType.EXCHANGE)) {
					calculated += cupom.getValue();
				}
			}
		}
		return new BigDecimal(calculated);
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}
	
}
