package com.dvsmedeiros.shopcart.domain;

import java.math.BigDecimal;
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
	private BigDecimal subTotal = BigDecimal.ZERO;
	private BigDecimal total = BigDecimal.ZERO;
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
		this.subTotal = BigDecimal.ZERO;
		for(CartItem item : cartItems){			
			this.subTotal = subTotal.add(item.getProduct().getPrice().getValue().multiply( BigDecimal.valueOf(item.getQuantity())));
		}
	}
	
	public List<CartItem> getCartItems() {
		return cartItems;
	}
	
	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Freight getFreight() {
		return freight;
	}

	public void setFreight(Freight freight) {
		this.freight = freight;
	}

	
}
