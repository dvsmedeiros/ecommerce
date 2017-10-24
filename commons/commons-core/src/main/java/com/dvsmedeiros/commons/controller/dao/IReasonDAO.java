package com.dvsmedeiros.commons.controller.dao;

import java.util.List;

import com.dvsmedeiros.bce.core.dao.IDAO;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.commons.domain.Reason;

public interface IReasonDAO extends IDAO<Reason> {
	
	List<Reason> filter(Filter<Reason> aEntity);
	
}
