package com.dvsmedeiros.stock.service;

import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvsmedeiros.bce.core.controller.IFacade;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCase;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.bce.domain.Result;
import com.dvsmedeiros.commons.domain.User;
import com.dvsmedeiros.commons.service.CommonsController;
import com.dvsmedeiros.rest.domain.ResponseMessage;
import com.dvsmedeiros.stock.domain.Stock;
import com.dvsmedeiros.stock.domain.StockRecord;

@Controller
@SuppressWarnings({ "unchecked", "rawtypes" })
@RequestMapping("${bce.app.context}/stock")
public class StockController extends CommonsController<Stock> {

	@Autowired
	@Qualifier("applicationFacade")
	private IFacade appFacade;
	
	public StockController() {
		super(Stock.class);
	}
	
	@Override
	public @ResponseBody ResponseEntity createEntity(@RequestBody Stock entity) {
		
		User loggedUser = getLoggedUser();
		
		if(entity != null && entity.getRecords() != null && ! entity.getRecords().isEmpty() ) {
			entity.getRecords().get(0).setUser(loggedUser);
		}
		
		return super.createEntity(entity);
	}
	
	@RequestMapping(value = "record", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity saveStockRecord(@RequestBody StockRecord record) {

		try {
			
			User loggedUser = getLoggedUser();
			record.setUser(loggedUser);
			
			BusinessCase aCase = new BusinessCaseBuilder().withName("SAVE_STOCKRECORD").build();			
			Result result = appFacade.save(record, aCase);

			if (result.hasError()) {
				return new ResponseEntity(new ResponseMessage(Boolean.TRUE, result.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			return new ResponseEntity<StockRecord>(record, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new ResponseMessage(Boolean.TRUE, "Erro ao inserir registro."), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
