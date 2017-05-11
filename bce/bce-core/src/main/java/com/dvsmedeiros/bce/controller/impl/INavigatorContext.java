package com.dvsmedeiros.bce.controller.impl;

import java.util.Map;

public interface INavigatorContext {
	
	public Map<String, Object> getAttributes();
	public void setAttribute(String key, Object attribute);
	//public Object getAttribute(String key);
	public <R> R getAttribute(String key);
	
}
