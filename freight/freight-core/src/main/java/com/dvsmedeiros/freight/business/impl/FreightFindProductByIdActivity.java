package com.dvsmedeiros.freight.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.commons.controller.INavigationCase;
import com.dvsmedeiros.commons.controller.INavigator;
import com.dvsmedeiros.commons.controller.business.IStrategy;
import com.dvsmedeiros.commons.controller.impl.BusinessCase;
import com.dvsmedeiros.commons.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.freight.domain.FreightFilter;
import com.dvsmedeiros.product.domain.Product;

@Component
public class FreightFindProductByIdActivity implements IStrategy<FreightFilter> {

	@Autowired
	@Qualifier("navigator")
	INavigator navigator;

	@Override
	public void process(FreightFilter aEntity, INavigationCase<FreightFilter> aCase) {

		if (aEntity.getEntity().getProduct() != null) {

			BusinessCase<Product> bCase = new BusinessCaseBuilder<Product>().withName("FIND_PRODUCT_BY_ID").build();
			navigator.run(aEntity.getEntity().getProduct(), bCase);

			if (bCase.isSuspendExecution() || bCase.getResult().getEntity() == null) {
				aCase.suspendExecution();
				aCase.getResult().setMessage(bCase.getResult().getMessage());
				return;
			}

			aEntity.getEntity().setProduct(bCase.getResult().getEntity());

		}
	}

}
