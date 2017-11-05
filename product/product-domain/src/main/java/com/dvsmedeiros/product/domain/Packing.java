package com.dvsmedeiros.product.domain;

import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.domain.DomainEntity;

@Component
@Entity
@Table(name = "PACKINGS")
public class Packing extends DomainEntity {

	private PackingType type = PackingType.BOX;
	private BigDecimal weight = BigDecimal.ZERO;
	private BigDecimal height = BigDecimal.ZERO;
	private BigDecimal width = BigDecimal.ZERO;
	private BigDecimal length = BigDecimal.ZERO;
	private BigDecimal diameter = BigDecimal.ZERO;

	public PackingType getType() {
		return type;
	}

	public void setType(PackingType type) {
		this.type = type;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public BigDecimal getWidth() {
		return width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	public BigDecimal getLength() {
		return length;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	public BigDecimal getDiameter() {
		return diameter;
	}

	public void setDiameter(BigDecimal diameter) {
		this.diameter = diameter;
	}

}
