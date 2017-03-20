package com.dvsmedeiros.freight.business.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.dvsmedeiros.freight.domain.FreightRequest;
import com.dvsmedeiros.freight.domain.FreightService;

@Component
public class CalculateFreight implements IStrategy<Freight>{
	
	@Autowired
	private IAdapter<FreightRequest, Frete> freightToFreteAdpter;
	@Autowired
	private IAdapter<FreteRetorno, FreightService> freteRetornoToFreightResponse;
	
	private IFreteFacade freteFacade;
	private FreteConfig config;
	
	public CalculateFreight() {
		
		config = new FreteConfig();
		config.setAvisoRecebimento(true);
		config.setEmMaos(false);

		freteFacade = new CorreioFacade(config);
	}
	
	@Override
	public void process(Freight aEntity, INavigationCase<Freight> aCase) {
		
		aEntity.getRequest().setPostalCodeDestine("08696100");
				
		Frete frete = freightToFreteAdpter.adapt(aEntity.getRequest());
		List<FreteRetorno> result = freteFacade.calculaFrete(frete);
		
		List<FreightService> freights = new ArrayList<>();
		
		for (FreteRetorno freteRetorno : result) {
			FreightService freightResponse = freteRetornoToFreightResponse.adapt(freteRetorno);
			freights.add(freightResponse);
		}
		
		aEntity.getResponse().setServices(freights);
		aCase.getResult().setEntity(aEntity);
	}
	
}
