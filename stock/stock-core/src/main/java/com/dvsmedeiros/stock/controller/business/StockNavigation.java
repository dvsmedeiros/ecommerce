package com.dvsmedeiros.stock.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.core.controller.business.impl.IdValidator;
import com.dvsmedeiros.bce.core.controller.business.impl.TestStrategy;
import com.dvsmedeiros.bce.core.controller.impl.Navigation;
import com.dvsmedeiros.bce.core.controller.impl.NavigationBuilder;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.stock.controller.business.impl.CreateStockRecord;
import com.dvsmedeiros.stock.controller.business.impl.FindFilterStock;
import com.dvsmedeiros.stock.controller.business.impl.FindStockZero;
import com.dvsmedeiros.stock.controller.business.impl.StockRecordFindRecordTypeByCode;
import com.dvsmedeiros.stock.controller.business.impl.StockRecordValidator;
import com.dvsmedeiros.stock.controller.dao.IStockDAO;
import com.dvsmedeiros.stock.domain.Stock;
import com.dvsmedeiros.stock.domain.StockRecord;

@Configuration
public class StockNavigation {
	
	@Autowired
	private CreateStockRecord createStockRecord;
	@Autowired
	private FindFilterStock findStockByProductId;
	@Autowired
	private StockRecordFindRecordTypeByCode stockRecordFindRecordTypeByCode;
	@Autowired
	private StockRecordValidator stockRecordValidator;
	@Autowired
	private FindStockZero findStockZero;
	
	@Bean(name = "CREATE_STOCK")
	public Navigation<Stock> createStock(){
		
		return new NavigationBuilder<Stock>()
				.next(createStockRecord)
				.build();
	}
	
	@Bean(name = "CREATE_STOCK_RECORD")
	public Navigation<StockRecord> createStockRecord(){
		
		return new NavigationBuilder<StockRecord>()
				.next(stockRecordValidator)
				.next(stockRecordFindRecordTypeByCode)
				.build();
	}
	
	@Bean(name = "FIND_FILTER_STOCK")
	public Navigation<Filter<Stock>> findStockByProductId(){
		
		return new NavigationBuilder<Filter<Stock>>()
				.next(findStockByProductId)
				.build();
	}
	
	@Bean(name = "FIND_STOCK_ZERO")
	public Navigation<Filter<Stock>> findStockZero(){
		
		return new NavigationBuilder<Filter<Stock>>()
				.next(findStockZero)
				.build();
	}
	
	
}
