package com.dvsmedeiros.order.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.commons.domain.User;
import com.dvsmedeiros.commons.service.CommonsController;
import com.dvsmedeiros.order.domain.Cupom;

@Controller
@RequestMapping("${bce.app.context}/cupom")
public class CupomController extends CommonsController<Cupom>{

	public CupomController() {
		super(Cupom.class);
	}
	
	@Override
	public @ResponseBody ResponseEntity<?> findEntityByFilter(@RequestBody Filter<Cupom> filter, @RequestParam(name="logged", required = false) boolean logged) {
		
		if(logged) {
			User loggedUser = getLoggedUser();
			filter.getEntity().setOwner(loggedUser);				
		}
		return super.findEntityByFilter(filter, logged);
		
	}
}
