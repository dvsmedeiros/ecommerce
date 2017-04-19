package com.dvsmedeiros.commons.domain;

public class LegalEntity extends People {

	private String companyName;
	private String tradingName;
	private CNPJ cnpj;
	private StateRegistration stateRegistration;
	private MunicipalRegistration municipalRegistration;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTradingName() {
		return tradingName;
	}

	public void setTradingName(String tradingName) {
		this.tradingName = tradingName;
	}

	public CNPJ getCnpj() {
		return cnpj;
	}

	public void setCnpj(CNPJ cnpj) {
		this.cnpj = cnpj;
	}

	public StateRegistration getStateRegistration() {
		return stateRegistration;
	}

	public void setStateRegistration(StateRegistration stateRegistration) {
		this.stateRegistration = stateRegistration;
	}

	public MunicipalRegistration getMunicipalRegistration() {
		return municipalRegistration;
	}

	public void setMunicipalRegistration(MunicipalRegistration municipalRegistration) {
		this.municipalRegistration = municipalRegistration;
	}

}
