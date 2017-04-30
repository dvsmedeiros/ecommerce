package com.dvsmedeiros.commons.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.domain.DomainEntity;

@Component
@Entity
@Table(name = "PHONES")
public class Phone extends DomainEntity {

	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
