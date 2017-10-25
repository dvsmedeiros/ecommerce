package com.dvsmedeiros.shopcart.controller.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.product.domain.Book;
import com.dvsmedeiros.product.domain.Product;
import com.dvsmedeiros.shopcart.domain.CartItem;
import com.dvsmedeiros.stock.controller.dao.impl.StockDAO;
import com.dvsmedeiros.stock.domain.Stock;

@Component
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CartItemHasStockValidator implements IStrategy<CartItem> {

	@Autowired
	@Qualifier("stockDAO")
	private StockDAO dao;

	@Override
	public void process(CartItem aEntity, INavigationCase<CartItem> aCase) {

		Filter<Stock> filter = new Filter(Stock.class);
		filter.getEntity().setProduct(new Product(aEntity.getProduct().getId()));
		List<Stock> stocks = dao.filter(filter);

		if (stocks != null && !stocks.isEmpty()) {
			Stock stock = stocks.get(0);
			if (stock.getCurrent().doubleValue() > 0 && stock.getCurrent().doubleValue() > aEntity.getQuantity()) {
				return;
			}
			aCase.suspendExecution();
			aCase.getResult().setMessage(((Book)stock.getProduct()).getTitle() + " não possui estoque!");
			return;
		}
		aCase.suspendExecution();
		aCase.getResult().setMessage("Estoque inexistente ou inválido");
		return;

	}
}
