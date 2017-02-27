package com.dvsmedeiros.product.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvsmedeiros.commons.controller.IFacade;
import com.dvsmedeiros.commons.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.commons.domain.Result;
import com.dvsmedeiros.commons.domain.Status;
import com.dvsmedeiros.commons.domain.StatusResponse;
import com.dvsmedeiros.product.domain.Category;
import com.dvsmedeiros.product.domain.CategoryResponse;
import com.dvsmedeiros.product.domain.Product;
import com.dvsmedeiros.product.domain.ProductResponse;

@Controller
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ProductController {

	@Autowired
	@Qualifier("applicationFacade")
	IFacade<Product> productFacade;

	@Autowired
	@Qualifier("applicationFacade")
	IFacade<Category> categoryFacade;

	@RequestMapping("product/create")
	public @ResponseBody Product product() {

		ProductResponse response = new ProductResponse();

		Product product = new Product();
		product.setActive(true);
		product.setCode("P01");
		product.setDescription("DESC P01");
		product.setInsertionDate(Calendar.getInstance());

		Result<Product> result = productFacade.save(product, new BusinessCaseBuilder().withName("SAVE_PRODUCT").build());

		if (result.hasError()) {
			response.setCode(Status.ERROR);
			response.setMessage(result.getMessage());
		}

		response.setProduct(product);
		return product;
	}

	@RequestMapping("product/list")
	public @ResponseBody List<Product> products() {

		Result<Product> result = productFacade.findAll(Product.class, new BusinessCaseBuilder().build());

		return result.getEntityList();

	}

	@RequestMapping(value = "products/category", method = RequestMethod.POST)
	public @ResponseBody StatusResponse saveProductCategory(@RequestBody Category category) {

		StatusResponse response = new StatusResponse();

		try {

			Result<Category> result = categoryFacade.save(category, new BusinessCaseBuilder().forEntity(Category.class).build());

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
	
	@RequestMapping(value = "products/category", method = RequestMethod.GET)
	public @ResponseBody CategoryResponse getProductCategories() {

		CategoryResponse response = new CategoryResponse();

		try {

			Result<Category> result = categoryFacade.findAll(Category.class, new BusinessCaseBuilder().build());
			
			if (result.hasError()) {
				response.setCode(Status.ERROR);
				response.setMessage(result.getMessage());
				return response;
			}
			response.setCategories(result.getEntityList());
			response.setCode(Status.OK);

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(Status.ERROR);
			response.setMessage("Erro ao cadastrar categoria.");
		}

		return response;
	}
}
