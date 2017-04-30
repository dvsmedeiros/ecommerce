package com.dvsmedeiros.supplier.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.commons.domain.LegalEntity;

@Component
@Entity
@Table(name = "SUPPLIERS")
public class Supplier extends LegalEntity {
		
}
