/**
 * 
 */
package com.bt.rtddashboard.configuration;


import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author 609669080
 *
 */
public class RTDDashboardInitializer extends AbstractAnnotationConfigDispatcherServletInitializer  {

	  @Override
	    protected Class<?>[] getRootConfigClasses() {
	        return new Class[] { RTDDashboardConfiguration.class };
	    }
	  
	    @Override
	    protected Class<?>[] getServletConfigClasses() {
	        return null;
	    }
	  
	    @Override
	    protected String[] getServletMappings() {
	        return new String[] { "/api/*" };
	    }
	    
	    @Override
	    protected Filter[] getServletFilters() {
	    	Filter [] singleton = { new CORSFilter()};
	    	return singleton;
	    }
}
