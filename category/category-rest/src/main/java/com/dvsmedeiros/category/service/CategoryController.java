package com.dvsmedeiros.category.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dvsmedeiros.category.domain.Category;
import com.dvsmedeiros.rest.rest.controller.DomainSpecificEntityController;

@Controller
@RequestMapping("${bce.app.context}/category")
@SuppressWarnings("rawtypes")
public class CategoryController extends DomainSpecificEntityController<Category>{

	public CategoryController() {
		super(Category.class);
	}
	/*
	@Override
	@Cacheable(value = "cacheCategories")
	public ResponseEntity getEntities() {
		return super.getEntities();
	}
	
	@Override
	@CacheEvict(value = "cacheCategories", allEntries = true)
	public ResponseEntity createEntity(Category entity) {
		// TODO Auto-generated method stub
		return super.createEntity(entity);
	}

	@Override
	@CacheEvict(value = "cacheCategories", allEntries = true)
	public ResponseEntity updateEntity(Category entity) {
		return super.updateEntity(entity);
	}
	
	@Override
	@CacheEvict(value = "cacheCategories", allEntries = true)
	public ResponseEntity deleteEntityById(Long id) {
		return super.deleteEntityById(id);
	}

	@Override
	@CacheEvict(value = "cacheCategories", allEntries = true)
	public ResponseEntity inactivateEntityById(Long id) {
		return super.inactivateEntityById(id);
	}
	*/
}
