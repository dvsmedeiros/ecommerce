package com.dvsmedeiros.address.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.address.controller.business.impl.AddressNumberValidator;
import com.dvsmedeiros.address.controller.business.impl.CityValidator;
import com.dvsmedeiros.address.controller.business.impl.NeighborhoodValidator;
import com.dvsmedeiros.address.controller.business.impl.StateValidator;
import com.dvsmedeiros.address.controller.business.impl.StreetValidator;
import com.dvsmedeiros.address.controller.business.impl.ZipCodeValidator;
import com.dvsmedeiros.address.domain.Address;
import com.dvsmedeiros.bce.controller.impl.EntityRuleDefinition;

@Configuration
public class AddressNavigation {
	
	@Autowired
	private ZipCodeValidator zipCodeValidator;
	@Autowired
	private StreetValidator streetValidator;
	@Autowired
	private AddressNumberValidator addressNumberValidator;
	@Autowired
	private NeighborhoodValidator neighborhoodValidator;
	@Autowired
	private CityValidator cityValidator;
	@Autowired
	private StateValidator stateValidator;
	
	
	@Bean(name="SAVE_ADDRESS")
	public EntityRuleDefinition<Address> getSaveProductNavigation(){
		
		EntityRuleDefinition<Address> activities = new EntityRuleDefinition<>();
		
		activities.addActivity(zipCodeValidator);
		activities.addActivity(streetValidator);
		activities.addActivity(addressNumberValidator);
		activities.addActivity(neighborhoodValidator);
		activities.addActivity(cityValidator);
		activities.addActivity(stateValidator);
		
		return activities;
	}
	
}
