package com.dvsmedeiros.order.controller.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.order.domain.Order;
import com.dvsmedeiros.order.domain.OrderItem;
import com.dvsmedeiros.product.domain.Book;
import com.dvsmedeiros.product.domain.Product;
import com.dvsmedeiros.stock.controller.dao.impl.StockDAO;
import com.dvsmedeiros.stock.domain.Stock;

@Component
@SuppressWarnings({ "unchecked", "rawtypes" })
public class OrderHasStockValidator implements IStrategy<Order> {

	@Autowired
	@Qualifier("stockDAO")
	private StockDAO stockDao;
	
	@Override
	public void process(Order aEntity, INavigationCase<Order> aCase) {
		
		StringBuilder builder = new StringBuilder();
		builder.append("Produto(s): ");
		if (aEntity != null && aEntity.getItems() != null && !aEntity.getItems().isEmpty()) {
			boolean hasError = false;
			for (OrderItem item : aEntity.getItems()) {
				if(! hasStockItem(item)) {
					hasError = true;
					builder.append(item.getProduct().getDescription());
					builder.append(" ");
				}
			}
			if(hasError) {				
				builder.append(" não possui(em) estoque suficiente no momento!");
				aCase.suspendExecution();
				aCase.getResult().setMessage(builder.toString());
			}
		}
	}

	private boolean hasStockItem(OrderItem item) {

		Filter<Stock> filter = new Filter(Stock.class);
		filter.getEntity().setProduct(new Product(item.getProduct().getId()));
		List<Stock> stocks = stockDao.filter(filter);

		if (stocks != null && !stocks.isEmpty()) {
			Stock stock = stocks.get(0);
			if (stock.getCurrent().doubleValue() > 0 && stock.getCurrent().doubleValue() >= item.getQuantity()) {				
				return true;
			}
			item.getProduct().setDescription(((Book)stock.getProduct()).getTitle().toUpperCase());			
			return false;
		}
		return false;
	}
}
