package com.dvsmedeiros.commons.domain;

import java.util.List;

import com.dvsmedeiros.bce.domain.DomainSpecificEntity;
//import com.dvsmedeiros.bce.domain.Period;
import com.dvsmedeiros.category.domain.Category;

public class Analyze extends DomainSpecificEntity {

	public static final String PERCENT_SALE_BY_CATEGORY_GENDER_AGE = "GRAPH_0001" ;
	public static final String QUANTITY_SALE_BY_CATEGORY_PERIOD = "GRAPH_0002";

	private List<Category> categories;
	//private Period period;

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	/*
	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}
	*/

}
