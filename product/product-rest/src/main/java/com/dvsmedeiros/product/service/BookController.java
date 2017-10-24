package com.dvsmedeiros.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dvsmedeiros.commons.controller.IReasonFacade;
import com.dvsmedeiros.commons.service.CommonsController;
import com.dvsmedeiros.product.domain.Book;

@Controller
@RequestMapping("${bce.app.context}/book")
public class BookController extends CommonsController<Book> {
	
	@Autowired
	@Qualifier("reasonFacade")
	private IReasonFacade<Book> reasonFacade;
		
	public BookController() {
		super(Book.class);		
	}

}
