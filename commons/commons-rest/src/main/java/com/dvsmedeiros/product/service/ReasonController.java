package com.dvsmedeiros.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvsmedeiros.bce.core.controller.IFacade;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCase;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.bce.domain.Result;
import com.dvsmedeiros.commons.domain.Reason;
import com.dvsmedeiros.rest.rest.controller.BaseController;

@Controller
@RequestMapping("${bce.app.context}/reason")
public class ReasonController extends BaseController {
	
	@Autowired
	@Qualifier("applicationFacade")
	private IFacade<Reason> appFacade;
	
	@RequestMapping(value = "{code}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getEntities(@PathVariable String code) {

		try {
			
			BusinessCase<Reason> aCase = new BusinessCaseBuilder<Reason>().build();
			Result result = appFacade.findAll(Reason.class, aCase);
			
			List<Reason> reasons = result.getEntities();
			List<Reason> rs = new ArrayList<>();
			
			if(reasons != null && !reasons.isEmpty()) {
				for (Reason reason : reasons) {
					if(reason.getCode().equalsIgnoreCase(code)) {
						rs.add(reason);
					}
				}
			}
			
			return new ResponseEntity<>(rs, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
