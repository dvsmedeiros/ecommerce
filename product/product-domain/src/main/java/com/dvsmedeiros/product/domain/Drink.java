package com.dvsmedeiros.product.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.supplier.domain.Supplier;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Component
@Entity
@Table(name = "DRINKS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Drink extends Product {

	@ManyToOne
	private Supplier supplier;
	
	private Double alchoholContent = 0D;
	@OneToMany(cascade = CascadeType.MERGE)
	@JsonManagedReference
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "DRINK_ID")
	private List<Igredient> igredients;

	public Drink() {
		this.igredients = new ArrayList<>();
	}

	public Double getAlchoholContent() {
		return alchoholContent;
	}

	public void setAlchoholContent(Double alchoholContent) {
		this.alchoholContent = alchoholContent;
	}

	public List<Igredient> getIgredients() {
		return igredients;
	}

	public void setIgredients(List<Igredient> igredients) {
		this.igredients = igredients;
	}

}
