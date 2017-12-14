package com.dvsmedeiros.commons.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dvsmedeiros.bce.domain.DomainSpecificEntity;
import com.fasterxml.jackson.annotation.JsonIdentityReference;

@Entity
@Table(name = "NOTIFICATIONS")
public class Notification extends DomainSpecificEntity {

	@ManyToOne
//	@JsonIdentityReference(alwaysAsId=true)
	private User owner;
	private boolean readed;

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public boolean isReaded() {
		return readed;
	}

	public void setReaded(boolean readed) {
		this.readed = readed;
	}

}
