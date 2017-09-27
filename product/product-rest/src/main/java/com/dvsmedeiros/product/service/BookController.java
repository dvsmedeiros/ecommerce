package com.dvsmedeiros.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvsmedeiros.bce.domain.Result;
import com.dvsmedeiros.commons.controller.IReasonFacade;
import com.dvsmedeiros.commons.domain.Reason;
import com.dvsmedeiros.product.domain.Book;
import com.dvsmedeiros.rest.domain.ResponseMessage;
import com.dvsmedeiros.rest.rest.controller.DomainSpecificEntityController;

@Controller
@RequestMapping("${bce.app.context}/book")
public class BookController extends DomainSpecificEntityController<Book> {
	
	@Autowired
	@Qualifier("reasonFacade")
	private IReasonFacade<Book> reasonFacade;
	
	public BookController() {
		super(Book.class);
	}
	/*
	@Override
	@Cacheable(value = "cacheBooks")
	public ResponseEntity getEntities() {
		return super.getEntities();
	}
	
	@Override
	@CacheEvict(value = "cacheBooks", allEntries = true)
	public ResponseEntity createEntity(Book entity) {
		return super.createEntity(entity);
	}
	
	@Override
	@CacheEvict(value = "cacheBooks", allEntries = true)
	public ResponseEntity updateEntity(Book entity) {
		return super.updateEntity(entity);
	}
	
	@Override
	@CacheEvict(value = "cacheBooks", allEntries = true)
	public ResponseEntity deleteEntityById(Long id) {
		return super.deleteEntityById(id);
	}
	*/

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "activate/{id}", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity activateEntityById(@PathVariable Long id, @RequestBody Reason reason) {

		try {

			Book entity = appFacade.find(id, clazz).getEntity();

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
	public @ResponseBody ResponseEntity inactivateEntityById(@PathVariable Long id, @RequestBody Reason reason) {

		try {

			Book entity = appFacade.find(id, clazz).getEntity();

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
}
