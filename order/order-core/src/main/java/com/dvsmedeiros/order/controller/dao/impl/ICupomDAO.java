package com.dvsmedeiros.order.controller.dao.impl;

 import java.util.List;

import com.dvsmedeiros.bce.core.dao.IDAO;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.order.domain.Cupom;

public interface ICupomDAO extends IDAO<Cupom> {
	
	public List<Cupom> filter(Filter<Cupom> filter);
}
