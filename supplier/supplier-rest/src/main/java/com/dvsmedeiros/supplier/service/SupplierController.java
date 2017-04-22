package com.dvsmedeiros.supplier.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import com.dvsmedeiros.supplier.domain.Supplier;

@Controller
@SuppressWarnings({ "unchecked", "rawtypes" })
public class SupplierController {

	@Autowired
	@Qualifier("applicationFacade")
	IFacade<Supplier> appFacade;

	@RequestMapping(value = "supplier", method = RequestMethod.POST)
	public @ResponseBody StatusResponse saveSupplier(@RequestBody Supplier supplier) {
		StatusResponse response = new StatusResponse();

		try {

			Result<Supplier> result = appFacade.save(supplier,
					new BusinessCaseBuilder().withName("SAVE_SUPPLIER").build());

			if (result.hasError()) {
				response.setCode(Status.ERROR);
				response.setMessage(result.getMessage());
				return response;
			}

			response.setCode(Status.OK);
			response.setMessage("Fornecedor cadastrado com sucesso.");

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(Status.ERROR);
			response.setMessage("Erro ao cadastrar fornecedor.");
		}

		return response;
	}

	@RequestMapping(value = "supplier", method = RequestMethod.PUT)
	public @ResponseBody StatusResponse updateProductSupplier(@RequestBody Supplier supplier) {

		StatusResponse response = new StatusResponse();

		try {

			Result<Supplier> result = appFacade.update(supplier, new BusinessCaseBuilder().forEntity(Supplier.class).build());

			if (result.hasError()) {
				response.setCode(Status.ERROR);
				response.setMessage(result.getMessage());
				return response;
			}

			response.setCode(Status.OK);
			response.setMessage("Fornecedor atualizado com sucesso.");

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(Status.ERROR);
			response.setMessage("Erro ao atualizar fornecedor.");
		}

		return response;
	}

	@RequestMapping(value = "supplier", method = RequestMethod.GET)
	public @ResponseBody List<Supplier> getProductCategories(
			@RequestParam(value = "active", required = false) boolean active) {

		Result<Supplier> result = null;

		try {

			result = appFacade.findAll(active, Supplier.class, new BusinessCaseBuilder().build());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result.getEntityList();
	}

	@RequestMapping(value = "supplier/{supplierId}", method = RequestMethod.GET)
	public @ResponseBody Supplier getProductSupplierById(@PathVariable Long supplierId) {

		Result<Supplier> result = null;

		try {

			result = appFacade.find(supplierId, Supplier.class, new BusinessCaseBuilder().build());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result.getEntity();
	}

	@RequestMapping(value = "supplier/{supplierId}", method = RequestMethod.DELETE)
	public @ResponseBody StatusResponse deleteProductSupplierById(@PathVariable Long supplierId) {

		StatusResponse response = new StatusResponse();

		try {

			Supplier supplier = appFacade.find(supplierId, Supplier.class, new BusinessCaseBuilder<Supplier>().build()).getEntity();
			appFacade.delete(supplier, new BusinessCaseBuilder().build());

			response.setCode(Status.OK);
			response.setMessage("Fornecedor removida com sucesso.");

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(Status.ERROR);
			response.setMessage("Erro ao remover Fornecedor");
		}

		return response;
	}

	@RequestMapping(value = "supplier/{supplierId}", method = RequestMethod.PUT)
	public @ResponseBody StatusResponse inactivateProductSupplierById(@PathVariable Long supplierId) {

		StatusResponse response = new StatusResponse();

		try {

			Supplier supplier = appFacade.find(supplierId, Supplier.class, new BusinessCaseBuilder<Supplier>().build()).getEntity();
			appFacade.delete(supplier.getCode(), Supplier.class, new BusinessCaseBuilder().build());

			response.setCode(Status.OK);
			response.setMessage("Fornecedor removida com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(Status.ERROR);
			response.setMessage("Erro ao remover Fornecedor");
		}

		return response;
	}
}
