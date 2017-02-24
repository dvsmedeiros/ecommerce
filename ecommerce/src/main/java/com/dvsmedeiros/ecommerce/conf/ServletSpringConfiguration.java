package com.dvsmedeiros.ecommerce.conf;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.dvsmedeiros.commons.conf.JPAConfiguration;

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

}
