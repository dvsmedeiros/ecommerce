package com.dvsmedeiros.stock.controller.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.dao.IDAO;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.product.domain.Price;
import com.dvsmedeiros.stock.domain.Stock;

@Component
public interface IStockDAO extends IDAO<Stock>{
	
	public List<Stock> filter(Filter<Stock> filter);
		
}
