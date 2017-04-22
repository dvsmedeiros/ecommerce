package com.dvsmedeiros.product.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@AttributeOverride(name = "value", column = @Column(name = "UNIT_TYPE"))
public enum UnitType {
	
	/**
	PC	Peça
	KG	Quilograma
	MT	Metro
	CJ	Conjunto
	UM	Unidade
	HR	Hora
	CX	Caixa
	JG	Jogo
	PR	Par
	PT	Pacote
	LT	Litro
	*/
	
	PC(1), KG(2), MT(3), CJ(4), UN(5), HR(6), CX(7), JG(8), PR(9), PT(10), LT(11);

	private int value;

	private UnitType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
