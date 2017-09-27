package com.dvsmedeiros.product.controller.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.bce.domain.Result;
import com.dvsmedeiros.category.domain.Category;
import com.dvsmedeiros.product.controller.dao.ICategoryDAO;

@Component
public class CategoryFilter extends ApplicationEntity implements IStrategy<Filter<Category>>{
	
	@Autowired
	@Qualifier("categoryDAO")
	private ICategoryDAO dao;
	
	@Override
	public void process(Filter<Category> aEntity, INavigationCase<Filter<Category>> aCase) {
		
		List<Category> categories = dao.filter(aEntity);
		aCase.getResult().addEntity(Result.RESULTS_KEY, categories);
		
		
	}


}
