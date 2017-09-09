package com.dvsmedeiros.freight.business.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;import com.dvsmedeiros.freight.domain.FreightFilter;
import com.dvsmedeiros.product.domain.Packing;
import com.dvsmedeiros.product.domain.PackingType;
import com.dvsmedeiros.product.domain.Price;
import com.dvsmedeiros.product.domain.Product;
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
			BigDecimal weight = BigDecimal.ZERO;
			BigDecimal diameter = BigDecimal.ZERO;
			BigDecimal declaredValue = BigDecimal.ZERO;
			
			for (CartItem cartItem : cartItems) {
				
				BigDecimal quantity = new BigDecimal(cartItem.getQuantity());
				length = length.add(cartItem.getProduct().getPacking().getLength()).multiply(quantity) ;
				height = height.add(cartItem.getProduct().getPacking().getHeight()).multiply(quantity);
				width = width.add(cartItem.getProduct().getPacking().getWidth()).multiply(quantity);
				weight = weight.add(cartItem.getProduct().getPacking().getWeight()).multiply(quantity);
				declaredValue = declaredValue.add(cartItem.getProduct().getPrice().getValue()).multiply(quantity);
				
			}
			
			Packing packing = new Packing();
			packing.setHeight(height);
			packing.setLength(length);
			packing.setWidth(width);
			packing.setWeight(weight);
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
