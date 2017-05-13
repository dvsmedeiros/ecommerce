package com.dvsmedeiros.product.service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvsmedeiros.bce.controller.IFacade;
import com.dvsmedeiros.bce.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.bce.domain.Result;
import com.dvsmedeiros.bce.domain.Status;
import com.dvsmedeiros.bce.domain.StatusResponse;
import com.dvsmedeiros.commons.domain.User;

@Controller
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UserController {
	
	
	@Qualifier("applicationFacade")
	private IFacade<User> appFacade;
		
	@RequestMapping(value = "singup", method = RequestMethod.POST)
	public @ResponseBody StatusResponse saveUser(@RequestBody User user) {

		StatusResponse response = new StatusResponse();

		try {

			Result result = appFacade.save(user, new BusinessCaseBuilder().withName("SINGUP").build());

			if (result.hasError()) {
				response.setCode(Status.ERROR);
				response.setMessage(result.getMessage());
				return response;
			}

			response.setCode(Status.OK);
			response.setMessage("Usuário cadastrado com sucesso.");

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(Status.ERROR);
			response.setMessage("Erro ao cadastrar usuário.");
		}

		return response;
	}

	@RequestMapping(value = "account/{userId}", method = RequestMethod.GET)
	public @ResponseBody User getUserById(@PathVariable Long userId) {

		Result result = null;

		try {

			result = appFacade.find(userId, User.class, new BusinessCaseBuilder<User>().build());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result.getEntity();
	}
}
