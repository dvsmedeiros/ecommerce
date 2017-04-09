package com.dvsmedeiros.address.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.domain.DomainSpecificEntity;

@Component
@Entity
public class City extends DomainSpecificEntity {

	@OneToOne(cascade = CascadeType.ALL)
	private State state;

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

}
