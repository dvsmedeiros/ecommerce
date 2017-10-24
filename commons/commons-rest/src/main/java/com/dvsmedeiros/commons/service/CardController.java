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
import com.dvsmedeiros.commons.domain.CreditCard;
import com.dvsmedeiros.commons.domain.User;
import com.dvsmedeiros.rest.domain.ResponseMessage;

@Controller
@RequestMapping("${bce.app.context}/card")
public class CardController extends CommonsController<CreditCard> {

	@Autowired
	@Qualifier("applicationFacade")
	private IFacade<User> appFacade;

	public CardController() {
		super(CreditCard.class);
	}

	@Override
	public @ResponseBody ResponseEntity<?> createEntity(@RequestBody CreditCard entity) {
		try {

			super.createEntity(entity);

			User loggedUser = getLoggedUser();
			if (entity != null && entity.getId() > 0) {
				updatePrincipal(entity, loggedUser);
				return new ResponseEntity<>(entity, HttpStatus.OK);
			}
			return new ResponseEntity<>(
					new ResponseMessage(Boolean.TRUE,
							"Não foi possivel associar endereço ao usuario: " + loggedUser.getFirstName()),
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

			User loggedUser = getLoggedUser();
			if (entity != null && entity.getId() > 0) {
				updatePrincipal(entity, loggedUser);
				return new ResponseEntity<>(entity, HttpStatus.OK);
			}
			return new ResponseEntity<>(
					new ResponseMessage(Boolean.TRUE,
							"Não foi possivel associar endereço ao usuario: " + loggedUser.getFirstName()),
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
		User loggedUser = getLoggedUser();
		if (loggedUser.getPrincipalCreditCard() != null) {
			return new ResponseEntity<>(loggedUser.getPrincipalCreditCard(), HttpStatus.OK);
		}
		return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
	}
	
	private void updatePrincipal(CreditCard entity, User loggedUser) {

		if (loggedUser != null) {

			if (loggedUser.getCards() != null) {

				if (entity.getPrincipal()) {
					for (CreditCard card : loggedUser.getCards()) {
						if (entity.getId() > 0 && entity.getId() != card.getId()) {
							card.setPrincipal(Boolean.FALSE);
						}
					}
				} else {
					boolean hasPrincipal = false;
					for(CreditCard card : loggedUser.getCards()) {
						if(card.getPrincipal() && entity.getId() != card.getId()) {
							hasPrincipal = true;
							break;
						}
					}
					if(!hasPrincipal) {
						entity.setPrincipal(Boolean.TRUE);
					}
				}
								

				if (!loggedUser.getCards().contains(entity)) {
					loggedUser.getCards().add(entity);
				} else {
					loggedUser.getCards().remove(entity);
					loggedUser.getCards().add(entity);
				}

			} else {
				loggedUser.setCards(Arrays.asList(entity));
			}
			appFacade.update(loggedUser, new BusinessCaseBuilder<>().build());
		}
	}
}
