package com.dvsmedeiros.product.controller.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.dao.impl.GenericDAO;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.product.controller.dao.IBookDAO;
import com.dvsmedeiros.product.domain.Book;

@Component
public class BookDAO extends GenericDAO<Book> implements IBookDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Book> filter(Filter<Book> filter) {
		boolean validFilter = filter != null && filter.getEntity() != null;

		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT b FROM ").append(Book.class.getName()).append(" b ");

		jpql.append(" WHERE 1=1 ");

		if (validFilter && filter.getEntity().getTitle() != null) {
			jpql.append(" AND b.title like :title");
		}

		if (validFilter && filter.getEntity().getSynopsis() != null) {
			jpql.append(" AND b.synopsis like :synopsis");
		}

		if (validFilter && filter.getEntity().getISBN() != null) {
			jpql.append(" AND b.isbn = :isbn");
		}

		if (validFilter && filter.getEntity().getBarCode() != null) {
			jpql.append(" AND b.barCode = :barcode");
		}

		if (validFilter && filter.getEntity().getYear() != null) {
			jpql.append(" AND b.year = :year");
		}

		if (validFilter && filter.getEntity().getCode() != null) {
			jpql.append(" AND b.code = :code");
		}

		if (validFilter && filter.getEntity().getPages() != null) {
			jpql.append(" AND b.pages = :pages");
		}

		if (validFilter && filter.getEntity().getEdition() != null) {
			jpql.append(" AND b.edition = :edition");
		}

		if (validFilter && filter.getEntity().getActive() != null) {
			jpql.append(" AND b.active = :active");
		}

		if (validFilter && filter.getEntity().getCategories() != null
				&& !filter.getEntity().getCategories().isEmpty()) {
			jpql.append(" AND b.categories IN ( :categories )");
		}

		if (validFilter && filter.getEntity().getAuthors() != null && !filter.getEntity().getAuthors().isEmpty()) {
			jpql.append(" AND b.authors IN (:authors)");
		}

		if (validFilter && filter.getEntity().getPublishers() != null
				&& !filter.getEntity().getPublishers().isEmpty()) {
			jpql.append(" AND b.publishers IN (:publishers)");
		}

		TypedQuery<Book> query = em.createQuery(jpql.toString(), Book.class);

		if (validFilter && filter.getEntity().getTitle() != null) {
			query.setParameter("title", "%" + filter.getEntity().getTitle() + "%");
		}

		if (validFilter && filter.getEntity().getSynopsis() != null) {
			query.setParameter("title", "%" + filter.getEntity().getSynopsis() + "%");
		}

		if (validFilter && filter.getEntity().getISBN() != null) {
			query.setParameter("isbn", filter.getEntity().getISBN());
		}

		if (validFilter && filter.getEntity().getBarCode() != null) {
			query.setParameter("barcode", filter.getEntity().getBarCode());
		}

		if (validFilter && filter.getEntity().getYear() != null) {
			query.setParameter("year", filter.getEntity().getYear());
		}

		if (validFilter && filter.getEntity().getCode() != null) {
			query.setParameter("code", filter.getEntity().getCode());
		}

		if (validFilter && filter.getEntity().getPages() != null) {
			query.setParameter("pages", filter.getEntity().getPages());
		}

		if (validFilter && filter.getEntity().getEdition() != null) {
			query.setParameter("edition", filter.getEntity().getEdition());
		}

		if (validFilter && filter.getEntity().getActive() != null) {
			query.setParameter("active", filter.getEntity().getActive());
		}

		if (validFilter && filter.getEntity().getCategories() != null
				&& !filter.getEntity().getCategories().isEmpty()) {
			query.setParameter("categories", filter.getEntity().getCategories());
		}

		if (validFilter && filter.getEntity().getAuthors() != null && !filter.getEntity().getAuthors().isEmpty()) {
			query.setParameter("authors", filter.getEntity().getAuthors());
		}

		if (validFilter && filter.getEntity().getPublishers() != null
				&& !filter.getEntity().getPublishers().isEmpty()) {
			query.setParameter("publishers", filter.getEntity().getPublishers());
		}

		return query.getResultList();
	}

}
