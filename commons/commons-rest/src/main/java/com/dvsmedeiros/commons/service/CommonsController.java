package com.dvsmedeiros.commons.service;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvsmedeiros.bce.domain.DomainSpecificEntity;
import com.dvsmedeiros.bce.domain.Result;
import com.dvsmedeiros.commons.controller.IReasonFacade;
import com.dvsmedeiros.commons.domain.Reason;
import com.dvsmedeiros.commons.domain.User;
import com.dvsmedeiros.rest.domain.ResponseMessage;
import com.dvsmedeiros.rest.rest.controller.DomainSpecificEntityController;

public class CommonsController<T extends DomainSpecificEntity> extends DomainSpecificEntityController<T> {

	public CommonsController(Class<? extends T> clazz) {
		super(clazz);
	}

	@Autowired
	@Qualifier("reasonFacade")
	private IReasonFacade<T> reasonFacade;	
	
	@RequestMapping(value = "activate/{id}", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<?> activateEntityById(@PathVariable Long id, @RequestBody Reason reason) {

		try {
			
			T entity = appFacade.find(id, clazz).getEntity();

			Result result = reasonFacade.activate(clazz, entity.getCode(), reason);

			if (result.hasError()) {
				return new ResponseEntity<>(new ResponseMessage(Boolean.TRUE, result.getMessage()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(
					new ResponseMessage(Boolean.TRUE, "Erro ao ativar ".concat(clazz.getSimpleName().toLowerCase())),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "inactivate/{id}", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<?> inactivateEntityById(@PathVariable Long id, @RequestBody Reason reason) {

		try {

			T entity = appFacade.find(id, clazz).getEntity();

			Result result = reasonFacade.inactivate(clazz, entity.getCode(), reason);

			if (result.hasError()) {
				return new ResponseEntity<>(new ResponseMessage(Boolean.TRUE, result.getMessage()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(
					new ResponseMessage(Boolean.TRUE, "Erro ao inativar ".concat(clazz.getSimpleName().toLowerCase())),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public User getLoggedUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
