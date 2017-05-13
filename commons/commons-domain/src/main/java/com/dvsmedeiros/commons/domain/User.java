package com.dvsmedeiros.commons.domain;

import java.util.Collection;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Component
@SessionScoped
@Entity
@Table(name = "USERS")
@SuppressWarnings("serial")
public class User extends Individual implements UserDetails {
	
	@Transient
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
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
		
		this.password = encoder.encode(password);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getUsername() {
		return super.getEmail();
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

}
