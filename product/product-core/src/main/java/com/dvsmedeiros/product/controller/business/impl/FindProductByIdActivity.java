package com.dvsmedeiros.product.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.product.controller.dao.IProductDAO;
import com.dvsmedeiros.product.domain.Product;

@Component
public class FindProductByIdActivity implements IStrategy<Product> {

	@Autowired
	@Qualifier("productDAO")
	private IProductDAO dao;

	@Override
	public void process(Product aEntity, INavigationCase<Product> aCase) {

		Product product = dao.find(aEntity.getId());

		if (product == null) {
			aCase.suspendExecution();
			aCase.getResult().setMessage("Produto ID: " + aEntity.getId() + " não existe!");
			return;
		}
		aCase.getContext().setAttribute("product", product);
		
	}

}
