package com.dvsmedeiros.product.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dvsmedeiros.product.domain.Author;
import com.dvsmedeiros.rest.rest.controller.DomainSpecificEntityController;

@Controller
@RequestMapping("${bce.app.context}/author")
public class AuthorController extends DomainSpecificEntityController<Author>{

	public AuthorController() {
		super(Author.class);
	}
}
