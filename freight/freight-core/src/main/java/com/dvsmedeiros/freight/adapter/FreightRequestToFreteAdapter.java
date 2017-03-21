package com.dvsmedeiros.freight.adapter;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.commons.controller.IAdapter;
import com.dvsmedeiros.correiows.domain.Frete;
import com.dvsmedeiros.freight.domain.FreightRequest;

@Component
public class FreightRequestToFreteAdapter implements IAdapter<FreightRequest, Frete>{
	
	private static final String POSTAL_CODE_ORIGIN = "60743410";
	
	@Override
	public Frete adapt(FreightRequest source) {
		
		Frete frete = new Frete();
		
		frete.setAltura(source.getProduct().getPacking().getHeight());
		frete.setCepOrigem(POSTAL_CODE_ORIGIN);
		frete.setCepDestino(source.getPostalCodeDestine());
		frete.setComprimento(source.getProduct().getPacking().getLength());
		frete.setDiametro(source.getProduct().getPacking().getDiameter());
		frete.setFormato(source.getProduct().getPacking().getType().getValue());
		frete.setLargura(source.getProduct().getPacking().getWidth());
		frete.setPeso(source.getProduct().getPacking().getWeight().toString());
		frete.setValorDeclarado(source.getDeclaredValue());
		
		return frete;
	}

}
