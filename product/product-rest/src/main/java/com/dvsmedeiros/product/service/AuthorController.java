package com.dvsmedeiros.product.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dvsmedeiros.product.domain.Author;
import com.dvsmedeiros.rest.rest.controller.DomainSpecificEntityController;

@Controller
@RequestMapping("${bce.app.context}/author")
@SuppressWarnings("rawtypes")
public class AuthorController extends DomainSpecificEntityController<Author>{

	public AuthorController() {
		super(Author.class);
	}
	/*
	@Override
	@Cacheable(value = "cacheAuthors")
	public ResponseEntity getEntities() {
		return super.getEntities();
	}
	
	@Override
	@CacheEvict(value = "cacheAuthors", allEntries = true)
	public ResponseEntity createEntity(Author entity) {
		return super.createEntity(entity);
	}
	
	@Override
	@CacheEvict(value = "cacheAuthors", allEntries = true)
	public ResponseEntity updateEntity(Author entity) {
		return super.updateEntity(entity);
	}
	
	@Override
	@CacheEvict(value = "cacheAuthors", allEntries = true)
	public ResponseEntity deleteEntityById(Long id) {
		return super.deleteEntityById(id);
	}
	
	@Override
	@CacheEvict(value = "cacheAuthors", allEntries = true)
	public ResponseEntity inactivateEntityById(Long id) {
		return super.inactivateEntityById(id);
	}
	*/
}
