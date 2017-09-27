package com.dvsmedeiros.product.controller.dao;

import java.util.List;

import com.dvsmedeiros.bce.core.dao.IDAO;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.category.domain.Category;

public interface ICategoryDAO extends IDAO<Category> {

	List<Category> filter(Filter<Category> filter);
	
}
