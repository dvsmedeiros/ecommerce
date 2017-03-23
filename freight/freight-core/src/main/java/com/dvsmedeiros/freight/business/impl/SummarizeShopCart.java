package com.dvsmedeiros.freight.business.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.commons.controller.INavigationCase;
import com.dvsmedeiros.commons.controller.business.IStrategy;
import com.dvsmedeiros.freight.domain.Freight;
import com.dvsmedeiros.freight.domain.FreightFilter;
import com.dvsmedeiros.product.domain.Packing;
import com.dvsmedeiros.product.domain.PackingType;
import com.dvsmedeiros.product.domain.Price;
import com.dvsmedeiros.product.domain.Product;
import com.dvsmedeiros.shopcart.domain.Cart;
import com.dvsmedeiros.shopcart.domain.CartItem;

@Component
public class SummarizeShopCart implements IStrategy<FreightFilter> {

	@Override
	public void process(FreightFilter aEntity, INavigationCase<FreightFilter> aCase) {
		
		if(aEntity.getCartItems() != null && aEntity.getCartItems().size() > 0 ){
			
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
			
			Price price = new Price();
			price.setValue(declaredValue);
			
			Product product = new Product();
			product.setPrice(price);
			product.setPacking(packing);
			
			
			aEntity.getEntity().setProduct(product);
			
		}
	}

}
