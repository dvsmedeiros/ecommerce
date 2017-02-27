package com.dvsmedeiros.ecommerce.conf;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.dvsmedeiros.commons.conf.JPAConfiguration;
import com.dvsmedeiros.ecommerce.filter.CORSFilter;

public class ServletSpringConfiguration extends AbstractAnnotationConfigDispatcherServletInitializer{

    @Override
    protected Class < ? >[] getRootConfigClasses () {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { AppWebConfiguration.class, JPAConfiguration.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
    
    @Override
    protected Filter[] getServletFilters() {
        Filter [] singleton = { new CORSFilter() };
        return singleton;
    }
}
