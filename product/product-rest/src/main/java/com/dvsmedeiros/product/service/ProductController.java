package com.dvsmedeiros.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvsmedeiros.bce.controller.IFacade;
import com.dvsmedeiros.bce.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.bce.domain.Result;
import com.dvsmedeiros.bce.domain.Status;
import com.dvsmedeiros.bce.domain.StatusResponse;
import com.dvsmedeiros.product.domain.Product;

@Controller
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ProductController {

	@Autowired
	@Qualifier("applicationFacade")
	IFacade<Product> appFacade;

	@CacheEvict(value = "cacheProducts", allEntries = true)
	@RequestMapping(value = "products", method = RequestMethod.POST)
	public @ResponseBody StatusResponse saveProduct(@RequestBody Product product) {

		StatusResponse response = new StatusResponse();
		
		try {

			Result<Product> result = appFacade.save(product, new BusinessCaseBuilder().withName("SAVE_PRODUCT").build());

			if (result.hasError()) {
				response.setCode(Status.ERROR);
				response.setMessage(result.getMessage());
				return response;
			}

			response.setCode(Status.OK);
			response.setMessage("Produto cadastrado com sucesso.");

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(Status.ERROR);
			response.setMessage("Erro ao cadastrar produto.");
		}

		return response;

	}
	
	@RequestMapping(value = "products", method = RequestMethod.PUT)
	public @ResponseBody StatusResponse updateProduct(@RequestBody Product product) {

		StatusResponse response = new StatusResponse();

		try {

			Result<Product> result = appFacade.update(product,	new BusinessCaseBuilder().forEntity(Product.class).build());

			if (result.hasError()) {
				response.setCode(Status.ERROR);
				response.setMessage(result.getMessage());
				return response;
			}

			response.setCode(Status.OK);
			response.setMessage("Produto atualizado com sucesso.");

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(Status.ERROR);
			response.setMessage("Erro ao atualizar produto.");
		}

		return response;
	}

	@RequestMapping(value = "products/{productId}", method = RequestMethod.GET)
	public @ResponseBody Product getProductById(@PathVariable Long productId) {

		Result<Product> result = null;

		try {

			result = appFacade.find(productId, Product.class, new BusinessCaseBuilder<Product>().build());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result.getEntity();
	}

	@RequestMapping(value = "products/{productId}", method = RequestMethod.DELETE)
	public @ResponseBody StatusResponse deleteProductById(@PathVariable Long productId) {

		StatusResponse response = new StatusResponse();

		try {

			Product product = appFacade.find(productId, Product.class, new BusinessCaseBuilder<Product>().build()).getEntity();
			appFacade.delete(product, new BusinessCaseBuilder().build());

			response.setCode(Status.OK);
			response.setMessage("Produto removido com sucesso.");

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(Status.ERROR);
			response.setMessage("Erro ao remover Produto");
		}

		return response;
	}

	@RequestMapping(value = "products/{productId}", method = RequestMethod.PUT)
	public @ResponseBody StatusResponse deleteLogicalProductById(@PathVariable Long productId) {

		StatusResponse response = new StatusResponse();

		try {
			Product product = appFacade.find(productId, Product.class, new BusinessCaseBuilder<Product>().build()).getEntity();
			appFacade.delete(product.getCode(), Product.class, new BusinessCaseBuilder<Product>().build());

			response.setCode(Status.OK);
			response.setMessage("Produto removido com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(Status.ERROR);
			response.setMessage("Erro ao remover Produto.");
		}

		return response;
	}
	
	@Cacheable(value = "cacheProducts")
	@RequestMapping(value = "products", method = RequestMethod.GET)
	public @ResponseBody List<Product> getProducts(@RequestParam(value = "active", required = false) boolean active) {

		Result<Product> result = null;

		try {

			result = appFacade.findAll(active, Product.class, new BusinessCaseBuilder().build());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result.getEntityList();
	}
}
