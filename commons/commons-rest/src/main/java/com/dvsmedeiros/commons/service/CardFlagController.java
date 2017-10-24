package com.dvsmedeiros.commons.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dvsmedeiros.commons.domain.CardFlag;

@Controller
@RequestMapping("${bce.app.context}/flag")
public class CardFlagController extends CommonsController<CardFlag>{

	public CardFlagController() {
		super(CardFlag.class);
	}

}
