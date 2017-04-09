package com.dvsmedeiros.address.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvsmedeiros.address.domain.Address;
import com.dvsmedeiros.bce.controller.IFacade;
import com.dvsmedeiros.bce.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.bce.domain.Result;
import com.dvsmedeiros.bce.domain.Status;
import com.dvsmedeiros.bce.domain.StatusResponse;

@Controller
@SuppressWarnings({ "unchecked", "rawtypes" })
public class AddressController {
	
	@Autowired
	@Qualifier("applicationFacade")
	IFacade<Address> appFacade;

	
	@RequestMapping(value = "address", method = RequestMethod.POST)
	public @ResponseBody StatusResponse saveProduct(@RequestBody Address address) {

		StatusResponse response = new StatusResponse();
		
		try {

			Result<Address> result = appFacade.save(address, new BusinessCaseBuilder().withName("SAVE_ADDRESS").build());

			if (result.hasError()) {
				response.setCode(Status.ERROR);
				response.setMessage(result.getMessage());
				return response;
			}

			response.setCode(Status.OK);
			response.setMessage("Endereço cadastrado com sucesso.");

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(Status.ERROR);
			response.setMessage("Erro ao cadastrar endereço.");
		}

		return response;

	}
}
