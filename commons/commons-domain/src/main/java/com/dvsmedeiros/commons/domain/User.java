package com.dvsmedeiros.commons.domain;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Component
@SessionScoped
@Entity
@Table(name = "USERS")
public class User extends Individual {

	private String password;

	@OneToMany(cascade = CascadeType.ALL)
	@JsonManagedReference
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "USER_ID")
	private List<CreditCard> cards;

	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Role> roles;
	
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
