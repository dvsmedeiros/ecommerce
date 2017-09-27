package com.dvsmedeiros.stock.service;

import java.security.Principal;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvsmedeiros.bce.core.controller.IFacade;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCase;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.bce.domain.Result;
import com.dvsmedeiros.product.domain.Product;
import com.dvsmedeiros.rest.domain.ResponseMessage;
import com.dvsmedeiros.rest.rest.controller.BaseController;
import com.dvsmedeiros.stock.domain.Stock;
import com.dvsmedeiros.stock.domain.StockRecord;

@Controller
@SuppressWarnings({ "unchecked", "rawtypes" })
public class StockController extends BaseController {

	@Autowired
	@Qualifier("applicationFacade")
	private IFacade appFacade;

	@RequestMapping(value = "stock/{productId}", method = RequestMethod.GET)	
	public @ResponseBody ResponseEntity getStockByProductId(@PathVariable Long productId) {

		try {

			Filter<Stock> filter = new Filter<>(Stock.class);

			Product product = new Product();		
			if (productId != null) {
				product.setId(productId);
			}

			filter.getEntity().setProduct(product);

			Result result = appFacade.find(filter, new BusinessCaseBuilder<Filter<Stock>>().withName("FIND_FILTER_STOCK").build());
			
			if(result.hasError()) {
				return new ResponseEntity(new ResponseMessage(Boolean.FALSE, result.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			List<Stock> stocks = result.getEntity("stocks");
			return new ResponseEntity(stocks, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@RequestMapping(value = "stock", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity saveStock(@RequestBody Stock stock) {

		try {
			
			BusinessCase aCase = new BusinessCaseBuilder().withName("CREATE_STOCK").build();			
			Result result = appFacade.save(stock, aCase);

			if (result.hasError()) {
				return new ResponseEntity(new ResponseMessage(Boolean.TRUE, result.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			return new ResponseEntity<>(stock, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new ResponseMessage(Boolean.TRUE, "Erro ao inserir registro."), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@RequestMapping(value = "stock/record", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity saveStockRecord(@RequestBody StockRecord record, Principal principal) {

		try {
			
			BusinessCase aCase = new BusinessCaseBuilder().withName("CREATE_STOCK_RECORD").build();			
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
