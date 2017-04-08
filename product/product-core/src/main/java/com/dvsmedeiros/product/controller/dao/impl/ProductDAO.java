package com.dvsmedeiros.product.controller.dao.impl;

 import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.dvsmedeiros.bce.dao.impl.GenericSpecificDAO;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.product.controller.dao.IProductDAO;
import com.dvsmedeiros.product.domain.Product;

@Repository
@SuppressWarnings("unchecked")
public class ProductDAO extends GenericSpecificDAO<Product> implements IProductDAO{

	@Override
	public List<Product> find(Filter<Product> filter) {
		
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT p FROM ");
		jpql.append(Product.class.getName());
		jpql.append(" p ");
		jpql.append(" WHERE 1=1 ");
		
		if(filter != null & filter.getEntity() != null && filter.getEntity().getDescription() != null){
			jpql.append(" AND p.description like :description" );
		}
		
		Query query = em.createQuery(jpql.toString());
		
		if(filter != null & filter.getEntity() != null && filter.getEntity().getDescription() != null){
			query.setParameter("description", "%" + filter.getEntity().getDescription() + "%");
		}
		
		return query.getResultList();
	}

}
