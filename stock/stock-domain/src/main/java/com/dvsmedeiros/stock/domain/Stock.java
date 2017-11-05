package com.dvsmedeiros.stock.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import com.dvsmedeiros.bce.domain.DomainSpecificEntity;
import com.dvsmedeiros.product.domain.Product;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Component
@Entity
@Table(name = "STOCKS")
public class Stock extends DomainSpecificEntity {

	@ManyToOne
	private Product product;
	private BigDecimal min;
	private BigDecimal max;
	private BigDecimal reserved = BigDecimal.ZERO;

	@OneToMany(cascade = { CascadeType.DETACH, CascadeType.PERSIST })
	@JsonManagedReference
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "STOCK_ID")
	private List<StockRecord> records;
		
	public Stock() {
		this.records = new ArrayList<>();
	}
	
	public Stock(Product product, BigDecimal min, BigDecimal max){
		this.records = new ArrayList<>();
		this.product = product;
		this.min = min;
		this.max = max;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BigDecimal getMin() {
		return min;
	}

	public void setMin(BigDecimal min) {
		this.min = min;
	}

	public BigDecimal getMax() {
		return max;
	}

	public void setMax(BigDecimal max) {
		this.max = max;
	}

	public List<StockRecord> getRecords() {
		return records;
	}

	public void setRecords(List<StockRecord> records) {
		this.records = records;
	}

	public BigDecimal getCurrent() {
		BigDecimal sum = BigDecimal.ZERO;
		if(records != null && !records.isEmpty()) {				
			for (StockRecord record : records) {
				if(record.getRecordType().getCode().equals(RecordType.IN)) {
					sum = sum.add(record.getVolume());
				}else {
					sum = sum.subtract(record.getVolume());
				}
			}
		}
		return sum.subtract(getReserved());
	}

	public BigDecimal getReserved() {
		if(reserved != null) {
			return reserved;
		}
		return BigDecimal.ZERO;
	}

	public void setReserved(BigDecimal reserved) {
		this.reserved = reserved;
	}
	
}
