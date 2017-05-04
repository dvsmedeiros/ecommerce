package com.dvsmedeiros.order.controller.dao.impl;

 import java.util.List;

import com.dvsmedeiros.bce.dao.IDAO;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.order.domain.Order;

public interface IOrderDAO extends IDAO<Order> {
	
	public List<Order> filter(Filter<Order> filter);
}
