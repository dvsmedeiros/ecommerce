package com.dvsmedeiros.freight.adapter;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.commons.controller.IAdapter;
import com.dvsmedeiros.correiows.domain.FreteRetorno;
import com.dvsmedeiros.freight.domain.FreightResponse;
import com.dvsmedeiros.freight.domain.FreightService;

@Component
public class FreteRetoroToFreightServiceAdapter implements IAdapter<FreteRetorno, FreightService>{

	@Override
	public FreightService adapt(FreteRetorno source) {
		
		FreightService response = new FreightService();
		
		response.setValue(source.getValor());
		response.setDeadline(source.getPrazo());
		response.setServiceName(source.getTipoServico());
		
		return response;
		
	}

	
}
