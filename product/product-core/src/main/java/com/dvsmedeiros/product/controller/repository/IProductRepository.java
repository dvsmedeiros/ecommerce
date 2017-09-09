package com.dvsmedeiros.product.controller.repository;

import org.springframework.data.repository.CrudRepository;

import com.dvsmedeiros.product.domain.Product;

public interface IProductRepository extends CrudRepository<Product, Long>{
	
}
