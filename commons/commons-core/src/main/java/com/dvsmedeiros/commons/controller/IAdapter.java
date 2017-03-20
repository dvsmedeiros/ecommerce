package com.dvsmedeiros.commons.controller;

public interface IAdapter<S, D> {
	
	public D adapt(S source);
	
}
