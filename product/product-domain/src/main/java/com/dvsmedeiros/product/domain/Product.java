package com.dvsmedeiros.product.domain;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.domain.DomainSpecificEntity;
import com.dvsmedeiros.category.domain.Category;

@Component
@Entity
@Table(name = "PRODUCTS")
public class Product extends DomainSpecificEntity {

	@ManyToOne(cascade = CascadeType.ALL)
	private Packing packing;
	@Embedded
	@AttributeOverride(name = "value", column = @Column(name = "SALE_PRICE"))
	private Price salePrice;
	@ManyToMany(cascade = CascadeType.MERGE)
	private List<Category> categories;
	@ManyToOne
	private PriceGroup priceGroup;
	private String barCode;

	public Product() {
	}
	
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	public Packing getPacking() {
		return packing;
	}

	public void setPacking(Packing packing) {
		this.packing = packing;
	}

	public Price getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Price salePrice) {
		this.salePrice = salePrice;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public PriceGroup getPriceGroup() {
		return priceGroup;
	}

	public void setPriceGroup(PriceGroup priceGroup) {
		this.priceGroup = priceGroup;
	}
	
}
