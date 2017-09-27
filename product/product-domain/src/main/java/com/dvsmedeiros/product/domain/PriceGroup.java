package com.dvsmedeiros.product.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.dvsmedeiros.bce.domain.DomainSpecificEntity;

@Entity
@Table(name = "PRICE_GROUPS")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PriceGroup extends DomainSpecificEntity {

	private Double percent;

	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
	}

}
