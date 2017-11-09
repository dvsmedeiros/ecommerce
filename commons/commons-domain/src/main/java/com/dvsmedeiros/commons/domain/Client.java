package com.dvsmedeiros.commons.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "CLIENTS")
public class Client extends Individual {

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST })
	private User user;

	@OneToMany(cascade = CascadeType.MERGE)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<CreditCard> cards;

	public CreditCard getPrincipalCreditCard() {
		if (cards != null && !cards.isEmpty()) {
			for (CreditCard creditCard : cards) {
				if (creditCard.getPrincipal() != null && creditCard.getPrincipal()) {
					return creditCard;
				}
			}
		}
		return null;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CreditCard> getCards() {
		return cards;
	}

	public void setCards(List<CreditCard> cards) {
		this.cards = cards;
	}

}
