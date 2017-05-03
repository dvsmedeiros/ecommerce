package com.dvsmedeiros.order.domain;

public enum StatusOrder {
	
	RECEBIDO(1), PAGAMENTO(2), SEPARACAO(3), TRANSPORTE(4), ENTREGUE(5), CANCELADO(6);

	private int value;

	private StatusOrder(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
