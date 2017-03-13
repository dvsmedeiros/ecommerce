package com.dvsmedeiros.product.service;

import java.io.IOException;

import com.dvsmedeiros.product.domain.Product;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {
	
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		
		String json = "{\"packing\":{\"weight\":\"50\",\"type\":1,\"length\":\"50\",\"height\":\"50\",\"width\":\"50\"},\"price\":{\"value\":\"50\"},\"category\":{\"id\":1},\"name\":\"teste\",\"code\":\"teste\",\"shortDescription\":\"teste\",\"description\":\"teste\"}";
		ObjectMapper mapper = new ObjectMapper();
		Product login = mapper.readValue(json, Product.class);
		System.out.println(login);
		
	}
}
