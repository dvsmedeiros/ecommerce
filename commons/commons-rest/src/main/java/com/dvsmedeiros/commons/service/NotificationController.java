package com.dvsmedeiros.commons.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.commons.domain.Notification;

@Controller
@RequestMapping("${bce.app.context}/notification")
public class NotificationController extends CommonsController<Notification> {

	public NotificationController() {
		super(Notification.class);
	}
	
	@Override
	public @ResponseBody ResponseEntity<?> findEntityByFilter(@RequestBody Filter<Notification> filter, boolean logged) {
		if(logged) {
			filter.getEntity().setOwner(getLoggedUser());
		}
		return super.findEntityByFilter(filter, logged);
	}
	
}
