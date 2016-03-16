package com.vdoshi3;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.vdoshi3.utils.JwtFilter;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class, SwaggerConfig.class, JPAConfig.class, JwtFilter.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/*" };
	}
	
//	@Override
//	  protected Filter[] getServletFilters() {
//	    return new Filter[] {
//	      new JwtFilter();
//	    };
//	  }
	
}
