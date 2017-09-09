package com.dvsmedeiros.address.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvsmedeiros.address.domain.Address;
import com.dvsmedeiros.bce.core.controller.IFacade;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.bce.domain.Result;
import com.dvsmedeiros.rest.domain.ResponseMessage;
import com.dvsmedeiros.rest.rest.controller.BaseController;


@Controller
@SuppressWarnings({ "unchecked", "rawtypes" })
public class AddressController extends BaseController {
	
	@Autowired
	@Qualifier("applicationFacade")
	IFacade<Address> appFacade;
	
	@RequestMapping(value = "address", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity saveProduct(@RequestBody Address address) {

		try {

			Result result = appFacade.save(address, new BusinessCaseBuilder().withName("SAVE_ADDRESS").build());

			if (result.hasError()) {
				return new ResponseEntity(new ResponseMessage(Boolean.TRUE, result.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}

			return new ResponseEntity(HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new ResponseMessage(Boolean.TRUE, "Erro ao Salvar Endereço"), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
}
