package com.dvsmedeiros.payment.domain;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.domain.DomainEntity;
import com.dvsmedeiros.commons.domain.CreditCard;

@Component
@Entity
@Table(name = "PAYMENTS")
public class Payment extends DomainEntity {

	private PaymentType paymentType;
	private StatusPayment statusPayment;
	private BigDecimal purchaseValue;
	private Quota quota;
	
	@ManyToOne(optional = true, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private CreditCard card;

	@Enumerated(EnumType.STRING)
	public PaymentType getPaymentType() {
		return paymentType;
	}

	@Enumerated(EnumType.STRING)
	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public StatusPayment getStatusPayment() {
		return statusPayment;
	}

	public void setStatusPayment(StatusPayment statusPayment) {
		this.statusPayment = statusPayment;
	}

	public BigDecimal getPurchaseValue() {
		return purchaseValue;
	}

	public void setPurchaseValue(BigDecimal purchaseValue) {
		this.purchaseValue = purchaseValue;
	}

	@Embedded
	public Quota getQuota() {
		return quota;
	}

	public void setQuota(Quota quota) {
		this.quota = quota;
	}

	public CreditCard getCard() {
		return card;
	}

	public void setCard(CreditCard card) {
		this.card = card;
	}

}
