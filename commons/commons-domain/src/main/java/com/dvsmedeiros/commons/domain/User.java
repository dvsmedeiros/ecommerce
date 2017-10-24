package com.dvsmedeiros.commons.domain;

import java.util.Collection;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@SessionScoped
@Entity
@Table(name = "USERS")
public class User extends Individual implements UserDetails {

	private String password;
	private String confirmPassword;

	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<CreditCard> cards;

	public CreditCard getPrincipalCreditCard() {
		if (cards != null && !cards.isEmpty()) {
			for (CreditCard creditCard : cards) {
				if (creditCard.getPrincipal()) {
					return creditCard;
				}
			}
		}
		return null;
	}

	public List<CreditCard> getCards() {
		return cards;
	}

	public void setCards(List<CreditCard> cards) {
		this.cards = cards;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String getUsername() {
		return this.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

}
