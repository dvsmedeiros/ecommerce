package com.dvsmedeiros.freight.adapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.controller.IAdapter;
import com.dvsmedeiros.correiows.domain.FreteRetorno;
import com.dvsmedeiros.freight.domain.Freight;
import com.dvsmedeiros.freight.domain.FreightService;

@Component
public class FreteRetoroToFreightServiceAdapter implements IAdapter<List<FreteRetorno>, List<Freight>> {

	@Override
	public List<Freight> adapt(List<FreteRetorno> source) {

		List<Freight> response = new ArrayList<>();

		for (FreteRetorno retorno : source) {

			Freight freight = new Freight();
			FreightService service = new FreightService();

			service.setValue(retorno.getValor());
			service.setDeadline(retorno.getPrazo());
			service.setServiceName(retorno.getTipoServico());

			freight.setService(service);
			response.add(freight);
		}
		return response;
	}

}
