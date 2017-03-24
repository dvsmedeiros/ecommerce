package com.dvsmedeiros.freight.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.commons.controller.IAdapter;
import com.dvsmedeiros.commons.controller.INavigationCase;
import com.dvsmedeiros.commons.controller.business.IStrategy;
import com.dvsmedeiros.correiows.controller.IFreteFacade;
import com.dvsmedeiros.correiows.controller.impl.CorreioFacade;
import com.dvsmedeiros.correiows.domain.Frete;
import com.dvsmedeiros.correiows.domain.FreteConfig;
import com.dvsmedeiros.correiows.domain.FreteRetorno;
import com.dvsmedeiros.freight.domain.Freight;
import com.dvsmedeiros.freight.domain.FreightFilter;

@Component
public class CalculateFreight implements IStrategy<FreightFilter> {

	@Autowired
	private IAdapter<Freight, Frete> freightToFreteAdpter;
	@Autowired
	private IAdapter<List<FreteRetorno>, List<Freight>> freteRetornoToFreightResponse;

	private IFreteFacade freteFacade;
	private FreteConfig config;

	public CalculateFreight() {

		config = new FreteConfig();
		config.setAvisoRecebimento(true);
		config.setEmMaos(false);
		config.setCepOrigem("08543250");
		freteFacade = new CorreioFacade(config);
	}

	@Override
	public void process(FreightFilter aEntity, INavigationCase<FreightFilter> aCase) {

		Frete frete = freightToFreteAdpter.adapt(aEntity.getEntity());
		List<FreteRetorno> result = freteFacade.calculaFrete(frete);

		List<Freight> response = freteRetornoToFreightResponse.adapt(result);

		aCase.getResult().setUncheckedEntity(response);

	}

}
