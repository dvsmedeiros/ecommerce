package com.dvsmedeiros.product.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.domain.DomainSpecificEntity;
import com.dvsmedeiros.category.domain.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Category> categories;
	@ManyToOne
	private PriceGroup priceGroup;
	private String barCode;

	public Product() {
		this.categories = Collections.EMPTY_LIST;
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
	
	@JsonSerialize
	public Price getCalculatedSalePrice() {
		if(priceGroup != null && priceGroup.getPercent() != null && priceGroup.getPercent() > 0) {
			BigDecimal percent = new BigDecimal(priceGroup.getPercent());
			BigDecimal price = salePrice.getValue();
			Price newPrice = new Price();
			newPrice.setValue(salePrice.getValue().multiply(percent).add(price));
			return newPrice;
		}
		return salePrice;
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
