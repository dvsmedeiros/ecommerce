package com.dvsmedeiros.commons.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.stereotype.Component;

@Component
@Embeddable
@AttributeOverride(name = "number", column = @Column(name = "CPF"))
public class CPF extends Document {

}
