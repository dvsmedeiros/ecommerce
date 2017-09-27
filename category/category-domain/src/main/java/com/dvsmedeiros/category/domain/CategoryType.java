package com.dvsmedeiros.category.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.dvsmedeiros.bce.domain.DomainSpecificEntity;

@Entity
@Table(name = "CATEGORY_TYPES")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CategoryType extends DomainSpecificEntity {
	
}
