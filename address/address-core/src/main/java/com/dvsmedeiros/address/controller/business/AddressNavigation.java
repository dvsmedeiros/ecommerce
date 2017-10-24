package com.dvsmedeiros.address.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.address.controller.business.impl.AddressNumberValidator;
import com.dvsmedeiros.address.controller.business.impl.AddressTypeValidator;
import com.dvsmedeiros.address.controller.business.impl.CityValidator;
import com.dvsmedeiros.address.controller.business.impl.FindStateByCode;
import com.dvsmedeiros.address.controller.business.impl.NeighborhoodValidator;
import com.dvsmedeiros.address.controller.business.impl.StateValidator;
import com.dvsmedeiros.address.controller.business.impl.StreetValidator;
import com.dvsmedeiros.address.controller.business.impl.ZipCodeValidator;
import com.dvsmedeiros.address.domain.Address;
import com.dvsmedeiros.bce.core.controller.impl.Navigation;
import com.dvsmedeiros.bce.core.controller.impl.NavigationBuilder;

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
	@Autowired
	private AddressTypeValidator addressTypeValidator;
	@Autowired
	private FindStateByCode findCityByCode;
	
	@Bean(name="SAVE_ADDRESS")
	public Navigation<Address> getSaveProductNavigation(){
		
		return new NavigationBuilder<Address>()
				.next(zipCodeValidator)
				.next(streetValidator)
				.next(addressNumberValidator)
				.next(neighborhoodValidator)
				.next(cityValidator)
				.next(stateValidator)
				.build();
	}
	
	@Bean(name="ADDRESS_VALIDATOR")
	public Navigation<Address> addressValidator(){
		
		return new NavigationBuilder<Address>()
				.next(zipCodeValidator)
				.next(streetValidator)
				.next(addressNumberValidator)
				.next(neighborhoodValidator)
				.next(cityValidator)
				.next(stateValidator)
				.next(addressTypeValidator)				
				.build();
	}
	
	@Bean(name="UPDATE_ADDRESS")
	public Navigation<Address> updateValidator(){
		
		return new NavigationBuilder<Address>()
				.build();
	}
	
}
