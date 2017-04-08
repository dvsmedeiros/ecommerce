package com.dvsmedeiros.bce.controller;

public interface IAdapter<S, D> {
	
	public D adapt(S source);
	
}
