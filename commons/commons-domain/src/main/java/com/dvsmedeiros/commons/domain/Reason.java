package com.dvsmedeiros.commons.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dvsmedeiros.bce.domain.DomainEntity;
import com.dvsmedeiros.bce.domain.DomainSpecificEntity;
import com.dvsmedeiros.category.domain.Category;

@Entity
@Table(name = "ACTIVE_HISTORY")
public class Reason extends DomainSpecificEntity {
	
	@ManyToOne(cascade = CascadeType.MERGE)
	private Category category;
	@Enumerated(EnumType.STRING)
	private ReasonType type;
	
	public Reason() {
	}
	
	public Reason(String description, Category category) {
		this.setDescription(description);
		this.category = category;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public ReasonType getType() {
		return type;
	}

	public void setType(ReasonType type) {
		this.type = type;
	}
	
}
