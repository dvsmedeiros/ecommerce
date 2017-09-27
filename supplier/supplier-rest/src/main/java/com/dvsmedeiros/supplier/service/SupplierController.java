package com.dvsmedeiros.supplier.service;

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
import com.dvsmedeiros.rest.domain.ResponseMessage;
import com.dvsmedeiros.rest.rest.controller.BaseController;
import com.dvsmedeiros.supplier.domain.Supplier;

@Controller
@SuppressWarnings({ "unchecked", "rawtypes" })
public class SupplierController extends BaseController {

	@Autowired
	@Qualifier("applicationFacade")
	IFacade<Supplier> appFacade;

	@CacheEvict(value = "cacheSuppliers", allEntries = true)
	@RequestMapping(value = "supplier", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity saveSupplier(@RequestBody Supplier supplier) {

		try {

			Result result = appFacade.save(supplier, new BusinessCaseBuilder().withName("SAVE_SUPPLIER").build());

			if (result.hasError()) {
				return new ResponseEntity(new ResponseMessage(Boolean.TRUE, result.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			return new ResponseEntity<Supplier>(supplier, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new ResponseMessage(Boolean.TRUE, "Erro ao cadastrar fornecedor."), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@CacheEvict(value = "cacheSuppliers", allEntries = true)
	@RequestMapping(value = "supplier", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<Supplier> updateProductSupplier(@RequestBody Supplier supplier) {

		try {

			Result result = appFacade.update(supplier, new BusinessCaseBuilder().forEntity(Supplier.class).build());

			if (result.hasError()) {
				return new ResponseEntity(new ResponseMessage(Boolean.TRUE, result.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			return new ResponseEntity<Supplier>(supplier, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new ResponseMessage(Boolean.TRUE, "Erro ao cadastrar fornecedor."), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Cacheable(value = "cacheSuppliers")
	@RequestMapping(value = "supplier", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getProductCategories(	@RequestParam(value = "active", required = false) Boolean active) {

		try {

			Result result;
			
			if(active != null) {
				result = appFacade.findAll(Supplier.class, active, new BusinessCaseBuilder().build());
			} else {
				result = appFacade.findAll(Supplier.class, new BusinessCaseBuilder().build());
			}
			
			List<Supplier> suppliers = result.getEntities();
			return new ResponseEntity(suppliers, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@RequestMapping(value = "supplier/{supplierId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Supplier> getProductSupplierById(@PathVariable Long supplierId) {

		try {

			Result result = appFacade.find(supplierId, Supplier.class);
			Supplier supplier = result.getEntity();
			return new ResponseEntity<>(supplier, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@CacheEvict(value = "cacheSuppliers", allEntries = true)
	@RequestMapping(value = "supplier/{supplierId}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity deleteProductSupplierById(@PathVariable Long supplierId) {

		try {

			Supplier supplier = appFacade.find(supplierId, Supplier.class).getEntity();
			appFacade.delete(supplier, new BusinessCaseBuilder().build());

			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ResponseMessage(Boolean.TRUE, "Erro ao remover Fornecedor"), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@CacheEvict(value = "cacheSuppliers", allEntries = true)
	@RequestMapping(value = "supplier/{supplierId}", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity inactivateProductSupplierById(@PathVariable Long supplierId) {

		try {

			Supplier supplier = appFacade.find(supplierId, Supplier.class).getEntity();
			supplier.setActive(Boolean.FALSE);
			Result result = appFacade.inactivate(supplier);
			
			if(result.hasError()) {
				return new ResponseEntity(new ResponseMessage(Boolean.TRUE, result.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}

			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new ResponseMessage(Boolean.TRUE, "Erro ao remover Fornecedor"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
