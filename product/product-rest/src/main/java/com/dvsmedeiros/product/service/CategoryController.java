package com.dvsmedeiros.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvsmedeiros.commons.controller.ISpecificFacade;
import com.dvsmedeiros.commons.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.commons.domain.Result;
import com.dvsmedeiros.commons.domain.Status;
import com.dvsmedeiros.commons.domain.StatusResponse;
import com.dvsmedeiros.product.domain.Category;

@Controller
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CategoryController {

	@Autowired
	@Qualifier("applicationFacade")
	ISpecificFacade<Category> appFacade;

	@RequestMapping(value = "products/category", method = RequestMethod.POST)
	public @ResponseBody StatusResponse saveProductCategory(@RequestBody Category category) {

		StatusResponse response = new StatusResponse();

		try {

			Result<Category> result = appFacade.save(category,
					new BusinessCaseBuilder().forEntity(Category.class).build());

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

	@RequestMapping(value = "products/category", method = RequestMethod.PUT)
	public @ResponseBody StatusResponse updateProductCategory(@RequestBody Category category) {

		StatusResponse response = new StatusResponse();

		try {

			Result<Category> result = appFacade.update(category,
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
			response.setMessage("Erro ao atualizada categoria.");
		}

		return response;
	}

	@RequestMapping(value = "products/category", method = RequestMethod.GET)
	public @ResponseBody List<Category> getProductCategories() {

		Result<Category> result = null;

		try {

			result = appFacade.findAll(Category.class, new BusinessCaseBuilder().build());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result.getEntityList();
	}

	@RequestMapping(value = "products/category/{categoryId}", method = RequestMethod.GET)
	public @ResponseBody Category getProductCategoryById(@PathVariable Long categoryId) {

		Result<Category> result = null;

		try {

			result = appFacade.find(categoryId, Category.class, new BusinessCaseBuilder().build());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result.getEntity();
	}

	@RequestMapping(value = "products/category/{categoryId}", method = RequestMethod.DELETE)
	public @ResponseBody StatusResponse deleteProductCategoryById(@PathVariable Long categoryId) {

		StatusResponse response = new StatusResponse();
		Result<Category> result = null;

		try {

			Category category = appFacade.find(categoryId, Category.class, new BusinessCaseBuilder().build())
					.getEntity();
			result = appFacade.delete(category, new BusinessCaseBuilder().build());

			response.setCode(Status.OK);
			response.setMessage("Categoria removida com sucesso.");

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(Status.ERROR);
			response.setMessage(result.getMessage());
		}

		return response;
	}

	@RequestMapping(value = "products/category/{categoryId}", method = RequestMethod.PUT)
	public @ResponseBody StatusResponse deleteLogicalProductCategoryById(@PathVariable Long categoryId) {

		StatusResponse response = new StatusResponse();
		Result<Category> result = null;

		try {
			Category category = appFacade.find(categoryId, Category.class, new BusinessCaseBuilder().build())
					.getEntity();
			result = appFacade.delete(category.getCode(), Category.class, new BusinessCaseBuilder().build());

			response.setCode(Status.OK);
			response.setMessage("Categoria removida com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(Status.ERROR);
			response.setMessage(result.getMessage());
		}

		return response;
	}

}
