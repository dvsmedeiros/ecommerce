package com.dvsmedeiros.commons.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.core.dao.impl.GenericDAO;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.category.domain.Category;
import com.dvsmedeiros.commons.domain.Reason;
import com.google.common.base.Strings;

@Component
public class ReasonCategoryValidator extends ApplicationEntity implements IStrategy<Reason>{

	@Autowired
	@Qualifier("genericDAO")
	private GenericDAO<Category> dao;
	
	@Override
	public void process(Reason aEntity, INavigationCase<Reason> aCase) {
		
		if(aEntity != null && aEntity.getCategory() != null && aEntity.getCategory() != null) {
			
			Category category = null;
			
			if(aEntity.getId() > 0) {
				category = dao.find(aEntity.getCategory().getId(), Category.class);
			} else if (!Strings.isNullOrEmpty(aEntity.getCategory().getCode())) {
				category = (Category) dao.find(Category.class, aEntity.getCategory().getCode());
			}
			
			if (category != null ) {
				aEntity.setCategory(category);
				return;
			}
		}

		aCase.suspendExecution();
		aCase.getResult().setMessage("Categoria inexistente ou inválida");
	}

}
