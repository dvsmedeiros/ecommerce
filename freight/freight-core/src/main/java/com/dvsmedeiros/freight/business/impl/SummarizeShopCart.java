package com.dvsmedeiros.freight.business.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.commons.controller.INavigationCase;
import com.dvsmedeiros.commons.controller.business.IStrategy;
import com.dvsmedeiros.freight.domain.Freight;
import com.dvsmedeiros.product.domain.Packing;
import com.dvsmedeiros.product.domain.PackingType;
import com.dvsmedeiros.product.domain.Product;
import com.dvsmedeiros.shopcart.domain.Cart;
import com.dvsmedeiros.shopcart.domain.CartItem;

@Component
public class SummarizeShopCart implements IStrategy<Cart> {

	@Override
	public void process(Cart aEntity, INavigationCase<Cart> aCase) {
		
		List<CartItem> cartItems = aEntity.getCartItems();
		
		BigDecimal length = BigDecimal.ZERO;
		BigDecimal height = BigDecimal.ZERO;
		BigDecimal width = BigDecimal.ZERO;
		BigDecimal diameter = BigDecimal.ZERO;
		BigDecimal declaredValue = BigDecimal.ZERO;
		
		for (CartItem cartItem : cartItems) {
			
			length = length.add(cartItem.getProduct().getPacking().getLength());
			height = height.add(cartItem.getProduct().getPacking().getHeight());
			width = width.add(cartItem.getProduct().getPacking().getWidth());
			declaredValue = declaredValue.add(cartItem.getProduct().getPrice().getValue());
			
		}				
		
		Packing packing = new Packing();
		packing.setHeight(height);
		packing.setLength(length);
		packing.setWidth(width);
		packing.setDiameter(diameter);
		packing.setType(PackingType.BOX);
		
		Product product = new Product();
		product.setPacking(packing);
		
		Freight freight = new Freight();
		freight.getRequest().setProduct(product);
		freight.getRequest().setDeclaredValue(declaredValue);
		
		aEntity.setFreight(freight);
	}

}
