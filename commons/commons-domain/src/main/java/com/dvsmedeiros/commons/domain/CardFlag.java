package com.dvsmedeiros.commons.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.domain.DomainSpecificEntity;

@Component
@Entity
@Table(name = "CARD_FLAGS")
public class CardFlag extends DomainSpecificEntity {

}
