package com.dvsmedeiros.product.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.commons.controller.INavigationCase;
import com.dvsmedeiros.commons.controller.business.IStrategy;
import com.dvsmedeiros.commons.dao.IDAO;
import com.dvsmedeiros.product.domain.Product;

@Component
public class FindProductByIdActivity implements IStrategy<Product> {

	@Autowired
	@Qualifier("com.dvsmedeiros.product.domain.Product")
	private IDAO<Product> dao;

	@Override
	public void process(Product aEntity, INavigationCase<Product> aCase) {

		Product product = dao.find(aEntity.getId(), Product.class);

		if (product == null) {
			aCase.suspendExecution();
			aCase.getResult().setMessage("Produto ID: " + aEntity.getId() + " não existe!");
			return;
		}

		aCase.getResult().setEntity(product);
	}

}
