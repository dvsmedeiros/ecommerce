package com.dvsmedeiros.product.controller.business.impl;

import java.util.List;

import javax.crypto.AEADBadTagException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.controller.INavigationCase;
import com.dvsmedeiros.bce.controller.business.IStrategy;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.product.controller.dao.impl.ProductDAO;
import com.dvsmedeiros.product.domain.Product;

@Component
public class FindFilterProduct implements IStrategy<Filter<Product>> {
	
	@Autowired
	@Qualifier("com.dvsmedeiros.product.domain.Product")
	private ProductDAO dao;
	
	@Override
	public void process(Filter<Product> aFilter, INavigationCase<Filter<Product>> aCase) {
		
		List<Product> products = dao.filter(aFilter);
		aCase.getResult().setUncheckedEntity(products);
		
	}

}