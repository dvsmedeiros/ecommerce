package com.dvsmedeiros.category.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dvsmedeiros.category.domain.Category;
import com.dvsmedeiros.rest.rest.controller.DomainSpecificEntityController;

@Controller
@RequestMapping("${bce.app.context}/category")
public class CategoryController extends DomainSpecificEntityController<Category>{

	public CategoryController() {
		super(Category.class);
	}
	
}
