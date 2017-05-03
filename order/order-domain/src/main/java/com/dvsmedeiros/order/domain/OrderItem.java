package com.dvsmedeiros.order.domain;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.commons.domain.Item;
import com.dvsmedeiros.product.domain.Product;

@Component
@Entity
@Table(name = "ORDER_ITEMS")
public class OrderItem extends Item {

	@ManyToOne(cascade = CascadeType.DETACH)
	private Product product;
	@ManyToOne(cascade = CascadeType.ALL)
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
