package com.dvsmedeiros.product.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dvsmedeiros.product.domain.Publisher;
import com.dvsmedeiros.rest.rest.controller.DomainSpecificEntityController;

@Controller
@RequestMapping("${bce.app.context}/publisher")
@SuppressWarnings("rawtypes")
public class PublisherController extends DomainSpecificEntityController<Publisher>{

	public PublisherController() {
		super(Publisher.class);
	}
	
	/*
	@Override
	@Cacheable(value = "cachePublishers")
	public ResponseEntity getEntities() {
		return super.getEntities();
	}
	
	@Override
	@CacheEvict(value = "cachePublishers", allEntries = true)
	public ResponseEntity createEntity(Publisher entity) {
		return super.createEntity(entity);
	}
	
	@Override
	@CacheEvict(value = "cachePublishers", allEntries = true)
	public ResponseEntity updateEntity(Publisher entity) {
		return super.updateEntity(entity);
	}
	
	@CacheEvict(value = "cachePublishers", allEntries = true)
	public ResponseEntity deleteEntityById(Long id) {
		return super.deleteEntityById(id);
	}
	
	@Override
	@CacheEvict(value = "cachePublishers", allEntries = true)
	public ResponseEntity inactivateEntityById(Long id) {
		return super.inactivateEntityById(id);
	}
	*/
}
