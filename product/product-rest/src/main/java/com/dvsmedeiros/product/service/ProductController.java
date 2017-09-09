package com.dvsmedeiros.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.bce.domain.Result;
import com.dvsmedeiros.product.domain.Category;
import com.dvsmedeiros.product.domain.Product;
import com.dvsmedeiros.rest.domain.ResponseMessage;
import com.dvsmedeiros.rest.rest.controller.BaseController;

@Controller
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ProductController extends BaseController {

	@Autowired
	@Qualifier("applicationFacade")
	private IFacade<Product> appFacade;

	@RequestMapping(value = "products", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity saveProduct(@RequestBody Product product) {

		try {

			Result result = appFacade.save(product, new BusinessCaseBuilder().withName("SAVE_PRODUCT").build());

			if (result.hasError()) {
				return new ResponseEntity(new ResponseMessage(Boolean.TRUE, result.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}

			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new ResponseMessage(Boolean.TRUE, "Erro ao cadastrar produto."), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@RequestMapping(value = "products", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity updateProduct(@RequestBody Product product) {

		try {

			Result result = appFacade.update(product, new BusinessCaseBuilder().forEntity(Product.class).build());

			if (result.hasError()) {
				return new ResponseEntity(new ResponseMessage(Boolean.TRUE, result.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}

			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new ResponseMessage(Boolean.TRUE, "Erro ao atualizar produto."), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "products/{productId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Product> getProductById(@PathVariable Long productId) {


		try {

			Result result = appFacade.find(productId, new BusinessCaseBuilder<Product>().build());
			Product product = result.getEntity();
			return new ResponseEntity<Product>(product, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "products/{productId}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity deleteProductById(@PathVariable Long productId) {

		try {

			Product product = appFacade.find(productId, new BusinessCaseBuilder<Product>().build()).getEntity();
			appFacade.delete(product, new BusinessCaseBuilder().build());

			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ResponseMessage(Boolean.TRUE, "Erro ao remover Produto"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "products/{productId}", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity deleteLogicalProductById(@PathVariable Long productId) {

		try {
			
			Product product = appFacade.find(productId, new BusinessCaseBuilder<Product>().build()).getEntity();
			Result result = appFacade.inactivate(product);
			
			if(result.hasError()) {
				return new ResponseEntity<>(new ResponseMessage(Boolean.TRUE, result.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			return new ResponseEntity<>(product, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ResponseMessage(Boolean.TRUE, "Erro ao remover Produto."), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "products", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getProducts(@RequestParam(value = "active", required = false) Boolean active,
			@RequestParam(value = "categoryId", required = false) Long categoryId) {

		try {

			Filter<Product> filter = new Filter<>(Product.class);

			Category category = new Category();
			if (categoryId != null) {
				category.setId(categoryId);
			}

			if (active != null) {
				filter.getEntity().setActive(active);
			}

			filter.getEntity().setCategory(category);

			Result result = appFacade.find(filter, new BusinessCaseBuilder().withName("FIND_FILTER_PRODUCT").build());
			
			if(result.hasError()) {
				return new ResponseEntity(new ResponseMessage(Boolean.FALSE, result.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			List<Product> products = result.getEntity("products");
			return new ResponseEntity(products, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
