package com.dvsmedeiros.product.controller.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.IFacade;
import com.dvsmedeiros.bce.core.controller.ITask;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.bce.core.dao.impl.ConfigurationDAO;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.bce.domain.Configuration;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.bce.domain.Result;
import com.dvsmedeiros.category.domain.Category;
import com.dvsmedeiros.commons.controller.IReasonFacade;
import com.dvsmedeiros.commons.domain.Reason;
import com.dvsmedeiros.product.domain.Book;
import com.dvsmedeiros.stock.domain.Stock;

@Component

@SuppressWarnings({ "unchecked", "rawtypes" })
public class InactivateBookTask extends ApplicationEntity implements ITask {

	@Autowired
	@Qualifier("configurationDAO")
	private ConfigurationDAO configDAO;

	@Autowired
	@Qualifier("applicationFacade")
	private IFacade<Stock> appFacade;

	@Autowired
	@Qualifier("reasonFacade")
	private IReasonFacade reasonFacade;

	@Override
	public void run() {
		
		getLogger(this.getClass()).debug("==================== INICIO: JOB INATIVAÇÃO DE LIVROS SEM ESTOQUE ====================");
		
		long start = System.currentTimeMillis();
		
		Result result = appFacade.find(new Filter<Stock>(),
				new BusinessCaseBuilder<Filter<Stock>>().withName("FIND_EMPTY_STOCK").build());

		List<Stock> stocks = result.getEntities();

		Category category = appFacade.find(Category.class, "CAT0016", new BusinessCaseBuilder().build()).getEntity();
		
		if (stocks != null && !stocks.isEmpty()) {
			
			for (Stock stock : stocks) {
				reasonFacade.inactivate(Book.class, stock.getProduct().getCode(), new Reason("Inativado automaticamente", category));
			}
		}
		
		int size = stocks != null ? stocks.size() : 0;
		long end = System.currentTimeMillis();
		
		getLogger(this.getClass()).debug("= TOTAL INATIVAÇOES: " + size);
		getLogger(this.getClass()).debug("= EXECUTADO EM: " + (end - start) + " (ms)");
		getLogger(this.getClass()).debug("==================== FIM   : JOB INATIVAÇÃO DE LIVROS SEM ESTOQUE ==================== ");
		
		
	}

	@Override
	public Long getFixedRate() {

		Configuration config = configDAO.findByCode(com.dvsmedeiros.commons.domain.Configuration.INACTIVATION_BOOK_RATE);
		Long rate = config.getLongValue();

		return rate;
	}

	@Override
	public boolean isRestart() {
		return true;
	}
}
