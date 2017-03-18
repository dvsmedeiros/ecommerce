package com.dvsmedeiros.shopcart.domain;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.commons.domain.DomainEntity;
import com.dvsmedeiros.freight.domain.Freight;

@Component
@SessionScoped
public class Cart extends DomainEntity {

	private List<CartItem> cartItems;
	private double subTotal;
	private double total;
	private Freight freight;

	public Cart() {
		this.cartItems = new ArrayList<>();
	}

	public void addItem(CartItem item) {
		this.cartItems.add(item);
		calculateSubTotal();
	}
	
	public void removeAllItem(CartItem item) {
		this.cartItems.remove(item);
		calculateSubTotal();
	}
	
	public void removeItem(CartItem item) {
		
		if(item.getQuantity() > 1){
			item.lessOneProduct();
			calculateSubTotal();
			return;
		}
		this.cartItems.remove(item);
		calculateSubTotal();
	}
	
	public void moreOneProduct(CartItem item){
		item.moreOneProduct();
		calculateSubTotal();
	}
	public void calculateSubTotal(){
		this.subTotal = 0;
		for(CartItem item : cartItems){
			this.subTotal += item.getProduct().getPrice().getValue() * item.getQuantity();
		}
	}
	
	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public double getTotal() {
		return subTotal /*+ freight.value*/;
	}

	public Freight getFreight() {
		return freight;
	}

	public void setFreight(Freight freight) {
		this.freight = freight;
	}

	
}
