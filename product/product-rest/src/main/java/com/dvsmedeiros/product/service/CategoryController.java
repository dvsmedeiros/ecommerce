package com.dvsmedeiros.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvsmedeiros.bce.controller.IFacade;
import com.dvsmedeiros.bce.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.bce.controller.impl.EntityRuleDefinition;
import com.dvsmedeiros.bce.domain.Result;
import com.dvsmedeiros.bce.domain.Status;
import com.dvsmedeiros.bce.domain.StatusResponse;
import com.dvsmedeiros.bce.service.BaseController;
import com.dvsmedeiros.product.domain.Category;
import com.dvsmedeiros.product.domain.Product;

@Controller
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CategoryController extends BaseController{

	@Autowired
	@Qualifier("applicationFacade")
	IFacade<Category> appFacade;

	@CacheEvict(value = "cacheCategories", allEntries = true)
	@RequestMapping(value = "products/category", method = RequestMethod.POST)
	public @ResponseBody StatusResponse saveProductCategory(@RequestBody Category category) {

		StatusResponse response = new StatusResponse();

		try {

			Result result = appFacade.save(category, new BusinessCaseBuilder().withName("SAVE_CATEGORY").build());

			if (result.hasError()) {
				response.setCode(Status.ERROR);
				response.setMessage(result.getMessage());
				return response;
			}

			response.setCode(Status.OK);
			response.setMessage("Categoria cadastrada com sucesso.");

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(Status.ERROR);
			response.setMessage("Erro ao cadastrar categoria.");
		}

		return response;
	}
	
	@CacheEvict(value = "cacheSuppliers", allEntries = true)
	@RequestMapping(value = "products/category", method = RequestMethod.PUT)
	public @ResponseBody StatusResponse updateProductCategory(@RequestBody Category category) {

		StatusResponse response = new StatusResponse();

		try {

			Result result = appFacade.update(category,
					new BusinessCaseBuilder().forEntity(Category.class).build());

			if (result.hasError()) {
				response.setCode(Status.ERROR);
				response.setMessage(result.getMessage());
				return response;
			}

			response.setCode(Status.OK);
			response.setMessage("Categoria atualizada com sucesso.");

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(Status.ERROR);
			response.setMessage("Erro ao atualizar categoria.");
		}

		return response;
	}
	
	@Cacheable(value = "cacheCategories")
	@RequestMapping(value = "products/category", method = RequestMethod.GET)
	public @ResponseBody List<Category> getProductCategories(@RequestParam(value = "active", required = false) boolean active) {

		Result result = null;

		try {

			result = appFacade.findAll(active, Category.class, new BusinessCaseBuilder().build());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result.getEntities();
	}

	@RequestMapping(value = "products/category/{categoryId}", method = RequestMethod.GET)
	public @ResponseBody Category getProductCategoryById(@PathVariable Long categoryId) {

		Result result = null;

		try {

			result = appFacade.find(categoryId, Category.class, new BusinessCaseBuilder().build());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result.getEntity();
	}
	
	@CacheEvict(value = "cacheSuppliers", allEntries = true)
	@RequestMapping(value = "products/category/{categoryId}", method = RequestMethod.DELETE)
	public @ResponseBody StatusResponse deleteProductCategoryById(@PathVariable Long categoryId) {

		StatusResponse response = new StatusResponse();

		try {

			Category category = appFacade.find(categoryId, Category.class, new BusinessCaseBuilder<Category>().build()).getEntity("entity");
			appFacade.delete(category, new BusinessCaseBuilder().build());

			response.setCode(Status.OK);
			response.setMessage("Categoria removida com sucesso.");

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(Status.ERROR);
			response.setMessage("Erro ao remover Categoria");
		}

		return response;
	}
	
	@CacheEvict(value = "cacheSuppliers", allEntries = true)
	@RequestMapping(value = "products/category/{categoryId}", method = RequestMethod.PUT)
	public @ResponseBody StatusResponse inactivateProductCategoryById(@PathVariable Long categoryId) {

		StatusResponse response = new StatusResponse();

		try {
			
			Category category = appFacade.find(categoryId, Category.class, new BusinessCaseBuilder<Category>().build()).getEntity("entity");
			appFacade.delete(category.getCode(), Category.class, new BusinessCaseBuilder().build());
			

			response.setCode(Status.OK);
			response.setMessage("Categoria removida com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(Status.ERROR);
			response.setMessage("Erro ao remover Categoria");
		}

		return response;
	}

}
