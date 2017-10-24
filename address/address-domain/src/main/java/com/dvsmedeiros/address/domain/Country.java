package com.dvsmedeiros.address.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.domain.DomainSpecificEntity;

@Component
@Entity
@Table(name = "COUNTRIES")
public class Country extends DomainSpecificEntity {

}
