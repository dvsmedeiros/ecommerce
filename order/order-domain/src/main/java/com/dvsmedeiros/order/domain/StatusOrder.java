package com.dvsmedeiros.order.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.domain.DomainSpecificEntity;

@Component
@Entity
@Table(name = "STATUS_ORDER")
public class StatusOrder extends DomainSpecificEntity {
	
	//PROCESSING, PAYMENT, SEPARATION, TRANSPORTATION, DELIVERY, CANCELED;

}
