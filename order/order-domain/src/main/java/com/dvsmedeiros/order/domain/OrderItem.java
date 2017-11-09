package com.dvsmedeiros.order.domain;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.commons.domain.Item;
import com.dvsmedeiros.product.domain.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;

@Component
@Entity
@Table(name = "ORDER_ITEMS")
public class OrderItem extends Item {

	@ManyToOne(cascade = { CascadeType.DETACH })
	private Product product;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JsonBackReference
	@JsonIdentityReference(alwaysAsId=true)
	private Order order;
	private BigDecimal salePrice;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

}
