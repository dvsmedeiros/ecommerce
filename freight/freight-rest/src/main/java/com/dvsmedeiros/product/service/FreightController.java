package com.dvsmedeiros.product.service;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tempuri.CResultado;
import org.tempuri.CServico;
import org.tempuri.CalcPrecoPrazoWS;
import org.tempuri.CalcPrecoPrazoWSLocator;
import org.tempuri.CalcPrecoPrazoWSSoap;

import com.dvsmedeiros.commons.controller.IFacade;
import com.dvsmedeiros.freight.domain.Freight;
import com.dvsmedeiros.freight.domain.FreightResponse;

@Controller
public class FreightController {

	@Autowired
	@Qualifier("applicationFacade")
	IFacade<Freight> appFacade;

	@RequestMapping(value = "freight", method = RequestMethod.GET)
	public @ResponseBody List<FreightResponse> calculteFreightAndDeadLine() {

		List<FreightResponse> response = null;

		Map<Integer, String> services = new HashMap<>();
		services.put(40010, "SEDEX");
		services.put(40045, "SEDEX a Cobrar");		
		services.put(41106, "PAC");

		try {

			CalcPrecoPrazoWS ws = new CalcPrecoPrazoWSLocator();

			CalcPrecoPrazoWSSoap soap = ws.getCalcPrecoPrazoWSSoap();

			CResultado result = soap.calcPrecoPrazo("", "", "40010,40045,40215,40290,41106", "12231090", "08696100",
					"22", 1, new BigDecimal(22), new BigDecimal(22), new BigDecimal(22), new BigDecimal(22), "N",
					new BigDecimal(22), "N");

			response = new ArrayList<>();

			for (CServico servico : result.getServicos()) {

				FreightResponse freight = new FreightResponse();
				if (servico.getErro().equals("0")) {
					freight.setDeadline(servico.getPrazoEntrega());
					freight.setServiceName(services.get(servico.getCodigo()));
					freight.setValue(servico.getValor());
				}
				response.add(freight);
			}

		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return response;
	}
}
