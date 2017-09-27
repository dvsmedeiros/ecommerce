package com.dvsmedeiros.category.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.type.descriptor.sql.JdbcTypeFamilyInformation.Family;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.domain.Configuration;
import com.dvsmedeiros.bce.domain.DomainSpecificEntity;

@Component
@Entity
@Table(name = "CATEGORIES")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Category extends DomainSpecificEntity {

	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private List<Configuration> configs;	
	@ManyToOne(cascade = CascadeType.MERGE)
	private CategoryType type;
	
	public Category() {
	}
	
	public List<Configuration> getConfigs() {
		return configs;
	}

	public void setConfigs(List<Configuration> configs) {
		this.configs = configs;
	}

	public CategoryType getType() {
		return type;
	}

	public void setType(CategoryType type) {
		this.type = type;
	}
	
}
