package com.dvsmedeiros.product.controller.dao;

 import java.util.List;

import com.dvsmedeiros.commons.dao.IDAO;
import com.dvsmedeiros.commons.domain.Filter;
import com.dvsmedeiros.product.domain.Product;

public interface IProductDAO extends IDAO<Product> {
	
	public List<Product> find(Filter<Product> filter);
}
