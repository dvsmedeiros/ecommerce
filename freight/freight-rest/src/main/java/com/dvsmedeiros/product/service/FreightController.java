package com.dvsmedeiros.product.service;


import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvsmedeiros.commons.controller.IFacade;
import com.dvsmedeiros.freight.domain.Freight;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

@Controller
public class FreightController {

	@Autowired
	@Qualifier("applicationFacade")
	IFacade<Freight> appFacade;
	
	
	@RequestMapping(value = "freight", method = RequestMethod.GET )
	public @ResponseBody String testApi(){
		
		String post = null;
		
		try {

			Client client = Client.create();

			WebResource webResource = client.resource("http://ws.correios.com.br/calculador/CalcPrecoPrazo.asmx");

			String input = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
			+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
			+ "<soap:Body>"
			+ "<CalcPrecoPrazoData xmlns=\"http://tempuri.org/\">"
			+ "<nCdEmpresa></nCdEmpresa>"
			+ "<sDsSenha></sDsSenha>"
			  + "<nCdServico>41106</nCdServico>"
			  + "<sCepOrigem>08696100</sCepOrigem>"
			  + "<sCepDestino>08696100</sCepDestino>"
			  + "<nVlPeso>22</nVlPeso>"
			  + "<nCdFormato>1</nCdFormato>"
			  + "<nVlComprimento>22.0</nVlComprimento>"
			  + "<nVlAltura>22.0</nVlAltura>"
			  + "<nVlLargura>22.0</nVlLargura>"
			  + "<nVlDiametro>0.0</nVlDiametro>"
			  + "<sCdMaoPropria>S</sCdMaoPropria>"
			  + "<nVlValorDeclarado>22.0</nVlValorDeclarado>"
			  + "<sCdAvisoRecebimento>S</sCdAvisoRecebimento>"
			  + "<sDtCalculo></sDtCalculo>"
			  + "</CalcPrecoPrazoData>"
			  + "</soap:Body>"
			  + "</soap:Envelope>";

			post = webResource.type("text/xml").post(String.class, input);

			if (post == null) {
				throw new RuntimeException("Failed : HTTP error code : ");
			}

			System.out.println("Output from Server .... \n");
			System.out.println(post);

		} catch (Exception e) {

			e.printStackTrace();

		}
		
		return post;
	}
}
