package com.dvsmedeiros.product.service;

import java.util.List;

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

import com.dvsmedeiros.bce.core.controller.IFacade;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.bce.domain.Result;
import com.dvsmedeiros.product.domain.Category;
import com.dvsmedeiros.rest.domain.ResponseMessage;
import com.dvsmedeiros.rest.rest.controller.BaseController;

@Controller
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CategoryController extends BaseController{

	@Autowired
	@Qualifier("applicationFacade")
	IFacade<Category> appFacade;

	@CacheEvict(value = "cacheCategories", allEntries = true)
	@RequestMapping(value = "products/category", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity saveProductCategory(@RequestBody Category category) {

		try {

			Result result = appFacade.save(category, new BusinessCaseBuilder().withName("SAVE_CATEGORY").build());

			if (result.hasError()) {				
				return new ResponseEntity<>(new ResponseMessage(Boolean.TRUE, result.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ResponseMessage(Boolean.TRUE, "Erro ao cadastrar categoria."), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@CacheEvict(value = "cacheSuppliers", allEntries = true)
	@RequestMapping(value = "products/category", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity updateProductCategory(@RequestBody Category category) {

		try {

			Result result = appFacade.update(category, new BusinessCaseBuilder().forEntity(Category.class).build());

			if (result.hasError()) {
				return new ResponseEntity<>(new ResponseMessage(Boolean.TRUE, result.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ResponseMessage(Boolean.TRUE, "Erro ao atualizar categoria."), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@Cacheable(value = "cacheCategories")
	@RequestMapping(value = "products/category", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Category>> getProductCategories(@RequestParam(value = "active", required = false) Boolean active) {

		try {
			
			Result result;
			
			if(active != null) {
				result = appFacade.findAll(Category.class, active, new BusinessCaseBuilder().build());
			} else {
				result = appFacade.findAll(Category.class, new BusinessCaseBuilder().build());
			}
			
			List<Category> categories = result.getEntities();
			
			return new ResponseEntity<>(categories, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "products/category/{categoryId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Category> getProductCategoryById(@PathVariable Long categoryId) {

		try {

			Result result = appFacade.find(categoryId, new BusinessCaseBuilder().build());
			Category category = result.getEntity();
			
			return new ResponseEntity<>(category, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@CacheEvict(value = "cacheSuppliers", allEntries = true)
	@RequestMapping(value = "products/category/{categoryId}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity deleteProductCategoryById(@PathVariable Long categoryId) {

		try {

			Category category = appFacade.find(categoryId, new BusinessCaseBuilder<Category>().build()).getEntity();
			appFacade.delete(category, new BusinessCaseBuilder().build());

			return new ResponseEntity<>(HttpStatus.OK);			

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ResponseMessage(Boolean.TRUE, "Erro ao remover Categoria"), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@CacheEvict(value = "cacheSuppliers", allEntries = true)
	@RequestMapping(value = "products/category/{categoryId}", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity inactivateProductCategoryById(@PathVariable Long categoryId) {

		try {
			
			Category category = appFacade.find(categoryId, new BusinessCaseBuilder<Category>().build()).getEntity();
			Result result = appFacade.inactivate(category);
			
			if(result.hasError()) {
				return new ResponseEntity<>(new ResponseMessage(Boolean.TRUE, result.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ResponseMessage(Boolean.TRUE, "Erro ao remover Categoria"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
