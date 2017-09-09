package com.dvsmedeiros.product.controller.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.product.controller.dao.IProductDAO;
import com.dvsmedeiros.product.domain.Product;

@Component
public class FindFilterProduct implements IStrategy<Filter<Product>> {
	
	@Autowired
	private IProductDAO dao;
	
	@Override
	public void process(Filter<Product> aFilter, INavigationCase<Filter<Product>> aCase) {
		
		List<Product> products = dao.filter(aFilter);
		aCase.getResult().addEntity("products", products);
		
	}

}
