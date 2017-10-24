package com.dvsmedeiros.address.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.address.domain.Address;
import com.dvsmedeiros.address.domain.City;
import com.dvsmedeiros.address.domain.State;
import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.core.dao.IDAO;
import com.dvsmedeiros.bce.domain.DomainSpecificEntity;


@Component
public class FindStateByCode implements IStrategy<Address>{

	@Autowired
	@Qualifier("genericDAO")
	private IDAO<State> dao;
	
	@Override
	public void process(Address aEntity, INavigationCase<Address> aCase) {
		/*
		State state = (State) dao.find(State.class, aEntity.getNeighborhood().getCity().getState().getCode());
		if(state != null) {
			aEntity.getNeighborhood().getCity().setState(state);			
		}
		*/
	}

}
