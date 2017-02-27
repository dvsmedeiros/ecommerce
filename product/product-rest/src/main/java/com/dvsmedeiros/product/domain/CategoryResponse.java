package com.dvsmedeiros.product.domain;

import java.util.List;

import com.dvsmedeiros.commons.domain.StatusResponse;

public class CategoryResponse extends StatusResponse {

	private List<Category> categories;

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

}
