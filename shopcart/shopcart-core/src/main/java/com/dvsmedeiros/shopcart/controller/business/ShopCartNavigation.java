package com.dvsmedeiros.shopcart.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.core.controller.impl.Navigation;
import com.dvsmedeiros.bce.core.controller.impl.NavigationBuilder;
import com.dvsmedeiros.shopcart.controller.business.impl.AddItemToCart;
import com.dvsmedeiros.shopcart.controller.business.impl.FindProductInCart;
import com.dvsmedeiros.shopcart.controller.business.impl.RemoveAllItemOfCart;
import com.dvsmedeiros.shopcart.controller.business.impl.RemoveItemOfCart;
import com.dvsmedeiros.shopcart.controller.business.impl.ShopCartFindProductByIdActivity;
import com.dvsmedeiros.shopcart.domain.CartItem;

@Configuration
public class ShopCartNavigation {
	
	@Autowired
	private ShopCartFindProductByIdActivity findProductByIdActivity;
	@Autowired
	private AddItemToCart addItemToCart;
	@Autowired
	private FindProductInCart findProductInCart;
	@Autowired
	private RemoveItemOfCart removeItemOfCart;
	@Autowired
	private RemoveAllItemOfCart removeAllItemOfCart;
	
	@Bean(name="ADD_ITEM_TO_CART")
	public Navigation<CartItem> addProductToCartNavigation(){
		
		return new NavigationBuilder<CartItem>()
				.next(findProductInCart)
				.next(findProductByIdActivity)
				.next(addItemToCart)
				.build();
		
	}
	
	@Bean(name="REMOVE_ITEM_TO_CART")
	public Navigation<CartItem> removeProductToCartNavigation(){
		
		return new NavigationBuilder<CartItem>()
				.next(findProductInCart)
				.next(removeItemOfCart)
				.build();
	}
	
	@Bean(name="REMOVE_ALL_ITEM_TO_CART")
	public Navigation<CartItem> removeAllProductToCartNavigation(){
		
		return new NavigationBuilder<CartItem>()
		.next(findProductInCart)
		.next(removeAllItemOfCart)
		.build();
		
	}
}
