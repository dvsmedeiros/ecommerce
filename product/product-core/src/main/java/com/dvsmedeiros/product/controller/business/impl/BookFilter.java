package com.dvsmedeiros.product.controller.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.bce.domain.Result;
import com.dvsmedeiros.product.controller.dao.IBookDAO;
import com.dvsmedeiros.product.domain.Book;

@Component
public class BookFilter extends ApplicationEntity implements IStrategy<Filter<Book>> {
	
	@Autowired
	@Qualifier("bookDAO")
	private IBookDAO dao;
	
	@Override
	public void process(Filter<Book> aEntity, INavigationCase<Filter<Book>> aCase) {
		
		List<Book> books = dao.filter(aEntity);
		aCase.getResult().addEntity(Result.RESULTS_KEY, books);
	}

}
