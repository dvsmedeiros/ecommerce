package com.dvsmedeiros.ecommerce.conf;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.dvsmedeiros.bce.conf.JPAConfiguration;
import com.dvsmedeiros.ecommerce.conf.security.SecurityWebApplicationInitializer;
import com.dvsmedeiros.ecommerce.filter.CORSFilter;

public class ServletSpringConfiguration extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppWebConfiguration.class, JPAConfiguration.class, SecurityWebApplicationInitializer.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		Filter[] singleton = { new CORSFilter() };
		return singleton;
	}
}
