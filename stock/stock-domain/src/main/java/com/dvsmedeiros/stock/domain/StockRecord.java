package com.dvsmedeiros.stock.domain;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.domain.DomainEntity;
import com.dvsmedeiros.commons.domain.User;
import com.dvsmedeiros.product.domain.Price;
import com.dvsmedeiros.product.domain.UnitType;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Component
@Entity
@Table(name = "STOCK_RECORDS")
public class StockRecord extends DomainEntity {

	@Embedded
	@AttributeOverride(name = "value", column = @Column(name = "PURCHASE_PRICE"))
	private Price purchasePrice;
	@Embedded
	@AttributeOverride(name = "value", column = @Column(name = "SALE_PRICE"))
	private Price salePrice;
	private BigDecimal volume;
	@Enumerated(EnumType.STRING)
	private UnitType unitType;
	private Calendar fabricationDate;
	private Calendar expiryDate;
	@ManyToOne(cascade = CascadeType.MERGE)	
	private RecordType recordType;
	@ManyToOne
	@JsonBackReference
	private Stock stock;
	@ManyToOne(cascade = CascadeType.DETACH)
	private User user;
	
	public StockRecord() {
		this.salePrice = new Price();
	}
	
	public Price getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Price purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Price getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Price salePrice) {
		this.salePrice = salePrice;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public UnitType getUnitType() {
		return unitType;
	}

	public void setUnitType(UnitType unitType) {
		this.unitType = unitType;
	}
	
	public Calendar getFabricationDate() {
		return fabricationDate;
	}

	public void setFabricationDate(Calendar fabricationDate) {
		this.fabricationDate = fabricationDate;
	}

	public Calendar getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Calendar expiryDate) {
		this.expiryDate = expiryDate;
	}

	public RecordType getRecordType() {
		return recordType;
	}

	public void setRecordType(RecordType recordType) {
		this.recordType = recordType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
