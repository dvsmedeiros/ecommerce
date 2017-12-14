package com.dvsmedeiros.commons.controller.dao;

 import java.util.List;

import com.dvsmedeiros.bce.core.dao.IDAO;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.commons.domain.Cupom;
import com.dvsmedeiros.commons.domain.Notification;

public interface INotificationDAO extends IDAO<Notification> {
	
	public List<Notification> filter(Filter<Notification> filter);
}
