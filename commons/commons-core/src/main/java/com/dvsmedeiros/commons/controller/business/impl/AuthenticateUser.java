package com.dvsmedeiros.commons.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.controller.INavigationCase;
import com.dvsmedeiros.bce.controller.business.IStrategy;
import com.dvsmedeiros.commons.domain.User;

@Component
public class AuthenticateUser implements IStrategy<User>{
	
	@Autowired
	private User loggedUser;
	
	@Override
	public void process(User aEntity, INavigationCase<User> aCase) {
		
		User user = aCase.getContext().getAttribute("user");
		
		loggedUser.setActive(user.getActive());
		loggedUser.setAddress(user.getAddress());
		loggedUser.setBornDate(user.getBornDate());
		loggedUser.setCards(user.getCards());
		loggedUser.setCode(user.getCode());
		loggedUser.setCpf(user.getCpf());
		loggedUser.setDescription(user.getDescription());
		loggedUser.setEmail(user.getEmail());
		loggedUser.setFirstName(user.getFirstName());
		loggedUser.setGender(user.getGender());
		loggedUser.setId(user.getId());
		loggedUser.setInsertionDate(user.getInsertionDate());
		loggedUser.setLastName(user.getLastName());
		loggedUser.setPassword(user.getPassword());
		loggedUser.setPhones(user.getPhones());
		loggedUser.setRoles(user.getRoles());
		loggedUser.setAuthenticated(true);
		
	}
	
}
