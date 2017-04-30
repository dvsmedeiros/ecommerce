package com.dvsmedeiros.product.controller.dao;

 import java.util.List;

import com.dvsmedeiros.bce.dao.IDAO;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.product.domain.Product;

public interface IProductDAO extends IDAO<Product> {
	
	public List<Product> filter(Filter<Product> filter);
}
