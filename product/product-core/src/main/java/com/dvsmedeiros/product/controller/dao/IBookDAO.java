package com.dvsmedeiros.product.controller.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.dao.IDAO;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.product.domain.Book;
import com.dvsmedeiros.product.domain.Product;

@Component
public interface IBookDAO extends IDAO<Book> {
	
	List<Book> filter(Filter<Book> filter);
	
}
