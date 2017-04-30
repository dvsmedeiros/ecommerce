package com.dvsmedeiros.address.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.domain.DomainSpecificEntity;

@Component
@Entity
@Table(name = "CITIES")
public class City extends DomainSpecificEntity {

	@OneToOne(cascade = CascadeType.PERSIST)
	private State state;

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

}
