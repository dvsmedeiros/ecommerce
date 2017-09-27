package com.dvsmedeiros.category.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dvsmedeiros.category.domain.CategoryType;
import com.dvsmedeiros.rest.rest.controller.DomainSpecificEntityController;

@Controller
@RequestMapping("${bce.app.context}/category/type")
public class CategoryTypeController extends DomainSpecificEntityController<CategoryType>{

	public CategoryTypeController() {
		super(CategoryType.class);
	}
	
}
