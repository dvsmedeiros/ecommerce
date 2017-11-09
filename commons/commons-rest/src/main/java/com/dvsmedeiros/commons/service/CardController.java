package com.dvsmedeiros.commons.service;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvsmedeiros.bce.core.controller.IFacade;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.commons.domain.Client;
import com.dvsmedeiros.commons.domain.CreditCard;
import com.dvsmedeiros.commons.domain.Individual;
import com.dvsmedeiros.rest.domain.ResponseMessage;

@Controller
@RequestMapping("${bce.app.context}/card")
public class CardController extends CommonsController<CreditCard> {

	@Autowired
	@Qualifier("applicationFacade")
	private IFacade<Individual> appFacade;

	public CardController() {
		super(CreditCard.class);
	}
	
	@Override
	public ResponseEntity<?> getEntities() {

		Client loggedClient = getLoggedClient();
		if (loggedClient != null && loggedClient.getCards() != null && !loggedClient.getCards().isEmpty()) {
			return new ResponseEntity<>(loggedClient.getCards(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@Override
	public @ResponseBody ResponseEntity<?> createEntity(@RequestBody CreditCard entity) {
		try {

			super.createEntity(entity);

			Client loggedClient = getLoggedClient();
			if (entity != null) {
				updatePrincipal(entity, loggedClient);
				return new ResponseEntity<>(entity, HttpStatus.OK);
			}
			return new ResponseEntity<>(
					new ResponseMessage(Boolean.TRUE,
							"Não foi possivel associar cartão ao usuario: " + loggedClient.getFirstName()),
					HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (Exception e) {

			e.printStackTrace();
			return new ResponseEntity<>(
					new ResponseMessage(Boolean.TRUE, "Erro ao cadastrar ".concat(clazz.getSimpleName().toLowerCase())),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public @ResponseBody ResponseEntity<?> updateEntity(@RequestBody CreditCard entity) {
		try {

			super.updateEntity(entity);

			Client loggedClient = getLoggedClient();
			if (entity != null && entity.getId() > 0) {
				updatePrincipal(entity, loggedClient);
				return new ResponseEntity<>(entity, HttpStatus.OK);
			}
			return new ResponseEntity<>(
					new ResponseMessage(Boolean.TRUE,
							"Não foi possivel associar cartão ao usuario: " + loggedClient.getFirstName()),
					HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (Exception e) {

			e.printStackTrace();
			return new ResponseEntity<>(
					new ResponseMessage(Boolean.TRUE, "Erro ao cadastrar ".concat(clazz.getSimpleName().toLowerCase())),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "principal", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getPrincipalCreditCard() {
		Client loggedClient = getLoggedClient();
		if (loggedClient.getPrincipalCreditCard() != null) {
			return new ResponseEntity<>(loggedClient.getPrincipalCreditCard(), HttpStatus.OK);
		}
		return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
	}
	
	private void updatePrincipal(CreditCard entity, Client loggedClient) {

		if (loggedClient != null) {

			if (loggedClient.getCards() != null) {

				if (entity.getPrincipal() != null && entity.getPrincipal()) {
					for (CreditCard card : loggedClient.getCards()) {
						if (entity.getId() > 0 && entity.getId() != card.getId()) {
							card.setPrincipal(Boolean.FALSE);
						}
					}
				} else {
					boolean hasPrincipal = false;
					for(CreditCard card : loggedClient.getCards()) {
						if(card.getPrincipal() != null && card.getPrincipal() && entity.getId() != card.getId()) {
							hasPrincipal = true;
							break;
						}
					}
					if(!hasPrincipal) {
						entity.setPrincipal(Boolean.TRUE);
					}
				}
								

				if (!loggedClient.getCards().contains(entity)) {
					loggedClient.getCards().add(entity);
				} else {
					loggedClient.getCards().remove(entity);
					loggedClient.getCards().add(entity);
				}

			} else {
				loggedClient.setCards(Arrays.asList(entity));
			}
			appFacade.update(loggedClient, new BusinessCaseBuilder<>().build());
		}
	}
}
