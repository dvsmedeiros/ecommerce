package com.dvsmedeiros.address.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
import com.dvsmedeiros.commons.domain.Client;
import com.dvsmedeiros.commons.service.CommonsController;
import com.dvsmedeiros.rest.domain.ResponseMessage;

@Controller
@RequestMapping("${bce.app.context}/address")
public class AddressController extends CommonsController<Address> {

	@Autowired
	@Qualifier("applicationFacade")
	private IFacade<Client> appFacade;

	public AddressController() {
		super(Address.class);
	}

	@Override
	public @ResponseBody ResponseEntity<?> createEntity(@RequestBody Address entity) {

		try {

			super.createEntity(entity);

			Client loggedClient = getLoggedClient();
			if (entity != null) {
				updateBilling(entity, loggedClient);
				return new ResponseEntity<>(entity, HttpStatus.OK);
			}
			return new ResponseEntity<>(
					new ResponseMessage(Boolean.TRUE,
							"Não foi possivel associar endereço ao usuario: " + loggedClient.getEmail()),
					HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (Exception e) {

			e.printStackTrace();
			return new ResponseEntity<>(
					new ResponseMessage(Boolean.TRUE, "Erro ao cadastrar ".concat(clazz.getSimpleName().toLowerCase())),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public @ResponseBody ResponseEntity<?> updateEntity(@RequestBody Address entity) {
		try {

			super.updateEntity(entity);

			Client loggedClient = getLoggedClient();
			if (entity != null && entity.getId() > 0) {
				updateBilling(entity, loggedClient);
				return new ResponseEntity<>(entity, HttpStatus.OK);
			}
			return new ResponseEntity<>(
					new ResponseMessage(Boolean.TRUE,
							"Não foi possivel associar endereço ao usuario: " + loggedClient.getEmail()),
					HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (Exception e) {

			e.printStackTrace();
			return new ResponseEntity<>(
					new ResponseMessage(Boolean.TRUE, "Erro ao cadastrar ".concat(clazz.getSimpleName().toLowerCase())),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "billing", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getBillingAddress() {
		Client loggedClient = getLoggedClient();
		if (loggedClient.getBillingAddress() != null) {
			return new ResponseEntity<>(loggedClient.getBillingAddress(), HttpStatus.OK);
		}
		return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "delivery", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getDeliveryAddresses() {
		Client loggedClient = getLoggedClient();
		if (loggedClient.getAddresses() != null && !loggedClient.getAddresses().isEmpty()) {
			List<Address> addresses = new ArrayList<>();
			for (Address address : loggedClient.getAddresses()) {
				if(address.getDelivery()) {
					addresses.add(address);
				}
			}
			return new ResponseEntity<>(addresses, HttpStatus.OK);
		}
		return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
	}
	
	private void updateBilling(Address entity, Client loggedClient) {

		if (loggedClient != null) {

			if (loggedClient.getAddresses() != null) {

				if (entity.getBilling()) {
					for (Address address : loggedClient.getAddresses()) {
						if(entity.getId() > 0 && entity.getId() != address.getId()) {
							address.setBilling(Boolean.FALSE);
						} 
					}
				} else {
					boolean hasBilling = false;
					for(Address address : loggedClient.getAddresses()) {
						if(address.getBilling() != null && address.getBilling() && entity.getId() != address.getId()) {
							hasBilling = true;
							break;
						}
					}
					if(!hasBilling) {
						entity.setBilling(Boolean.TRUE);
					}
				}

				if(!loggedClient.getAddresses().contains(entity)) {
					loggedClient.getAddresses().add(entity);
				} else {
					loggedClient.getAddresses().remove(entity);
					loggedClient.getAddresses().add(entity);
				}

			} else {
				loggedClient.setAddresses(Arrays.asList(entity));
			}
			appFacade.update(loggedClient, new BusinessCaseBuilder<>().build());
		}
	}
	
}
