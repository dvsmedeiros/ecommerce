package com.dvsmedeiros.order.controller.business.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.IFacade;
import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.bce.core.dao.impl.GenericDAO;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.bce.domain.Result;
import com.dvsmedeiros.commons.domain.User;
import com.dvsmedeiros.order.domain.Order;
import com.dvsmedeiros.order.domain.OrderItem;
import com.dvsmedeiros.order.domain.StatusOrder;
import com.dvsmedeiros.product.domain.Book;
import com.dvsmedeiros.product.domain.Product;
import com.dvsmedeiros.stock.controller.dao.IStockDAO;
import com.dvsmedeiros.stock.domain.RecordType;
import com.dvsmedeiros.stock.domain.Stock;
import com.dvsmedeiros.stock.domain.StockRecord;
import com.google.common.base.Strings;

@Component
public class UpdateStockByOrder extends ApplicationEntity implements IStrategy<Order> {

	@Autowired
	@Qualifier("genericDAO")
	private GenericDAO<?> dao;
	
	@Autowired
	@Qualifier("stockDAO")
	private IStockDAO stockDao;
	
	@Autowired
	@Qualifier("applicationFacade")
	private IFacade<StockRecord> appFacade;
	
	@Override
	public void process(Order aEntity, INavigationCase<Order> aCase) {
		
		RecordType type;
		
		if (aEntity.getStatusOrder().getCode().equals(StatusOrder.EXCHANGED)) {
			type = (RecordType) dao.find(RecordType.class, RecordType.IN);
		} else {
			type = (RecordType) dao.find(RecordType.class, RecordType.OUT);
		}
		
		User logged = aCase.getContext().getAttribute("logged");
		
		for (OrderItem item : aEntity.getItems()) {
			
			Filter<Stock> filter = new Filter(Stock.class);
			filter.getEntity().setProduct(new Product(item.getProduct().getId()));
			List<Stock> stocks = stockDao.filter(filter);
			
			if(stocks != null && !stocks.isEmpty()) {
				
				Stock stock = stocks.get(0);			
				StockRecord stockRecord = new StockRecord();
				stockRecord.setStock(stock);
				stockRecord.setVolume(new BigDecimal( item.getQuantity()));
				stockRecord.setRecordType(type);
				stockRecord.setUser(logged != null ? logged : aEntity.getCustumer().getUser());
				stockRecord.setSalePrice(item.getProduct().getCalculatedSalePrice());
				stockRecord.setPurchasePrice(stock.getProduct().getSalePrice());
				
				Result result = appFacade.save(stockRecord, new BusinessCaseBuilder<StockRecord>().withName("SAVE_STOCKRECORD").build());
				
				if (result.hasError()) {
					aCase.getResult().setMessage(result.getMessage());
				}
				continue;
			}
			aCase.getResult().setMessage("Estoque do produto: " + ((Book)item.getProduct()).getTitle().toUpperCase() + " não foi encontrado!\n");
		} 
		if(!Strings.isNullOrEmpty(aCase.getResult().getMessage())) {
			aCase.suspendExecution();
		}
	}
}
