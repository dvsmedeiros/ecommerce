package com.dvsmedeiros.payment.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.domain.DomainEntity;

@Component
@Entity
@Table(name = "PAYMENTS")
public class Payment extends DomainEntity {

	private PaymentType paymentType;
	private StatusPayment statusPayment;
	private BigDecimal purchaseValue;
	private BigDecimal quotaValue;

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

	public BigDecimal getQuotaValue() {
		return quotaValue;
	}

	public void setQuotaValue(BigDecimal quotaValue) {
		this.quotaValue = quotaValue;
	}

}
