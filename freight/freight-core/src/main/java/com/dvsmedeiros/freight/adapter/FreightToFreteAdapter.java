package com.dvsmedeiros.freight.adapter;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.IAdapter;
import com.dvsmedeiros.correiows.domain.Frete;
import com.dvsmedeiros.freight.domain.Freight;

@Component
public class FreightToFreteAdapter implements IAdapter<Freight, Frete>{
	
	@Override
	public Frete adapt(Freight source) {
		
		Frete frete = new Frete();
		
		frete.setAltura(source.getProduct().getPacking().getHeight());
		frete.setCepDestino(source.getPostalCodeDestine());
		frete.setComprimento(source.getProduct().getPacking().getLength());
		frete.setDiametro(source.getProduct().getPacking().getDiameter());
		frete.setFormato(source.getProduct().getPacking().getType().getValue());
		frete.setLargura(source.getProduct().getPacking().getWidth());
		frete.setPeso(source.getProduct().getPacking().getWeight().toString());
		frete.setValorDeclarado(source.getProduct().getSalePrice().getValue());
		
		return frete;
	}

}
