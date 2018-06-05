/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import java.util.EnumSet;

public class WebConfiguration implements WebApplicationInitializer {

	/**
	 * Class Logger.
	 */
	private static final Logger log = LoggerFactory.getLogger(WebConfiguration.class);

	/**
	 * The spring dispatcher servlet name.
	 */
	private static final String SPRING_SERVLET_NAME = "spring";

	private static final EnumSet<DispatcherType> DISPS = EnumSet.of(
			DispatcherType.REQUEST,
			DispatcherType.FORWARD,
			DispatcherType.ASYNC,
			DispatcherType.INCLUDE
	);

	@Override
	public void onStartup(ServletContext servletContext) {
		initSpring(servletContext);
		initCharacterEncodingFilter(servletContext);
		initEtagFilter(servletContext);
	}

	/**
	 * Initialize spring context and spring mvc dispatcher servlet.
	 *
	 * @param servletContext Servlet context.
	 */
	private void initSpring(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext context = initContext(servletContext);
		initSpringMvc(servletContext, context);
	}

	/**
	 * Initialize spring context.
	 *
	 * @param servletContext Servlet context.
	 * @return Spring context.
	 */
	private AnnotationConfigWebApplicationContext initContext(ServletContext servletContext) {
		log.info("Initialize Spring Context");

		String configLocation = SpringMvcConfiguration.class.getPackage().getName();
		log.debug("Config location : {}", configLocation);

		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation(configLocation);

		log.debug("Add context loader listener");
		servletContext.addListener(new ContextLoaderListener(context));

		return context;
	}

	/**
	 * Initialize spring MVC dispatcher servlet.
	 *
	 * @param servletContext Servlet context.
	 * @param context Spring context.
	 */
	private void initSpringMvc(ServletContext servletContext, AnnotationConfigWebApplicationContext context) {
		log.info("Initialize Spring Mvc");

		DispatcherServlet servlet = new DispatcherServlet(context);

		log.debug("Configure servlet mapping");
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet(SPRING_SERVLET_NAME, servlet);
		dispatcher.setLoadOnStartup(2);
		dispatcher.addMapping("/rest/*");
		dispatcher.addMapping("/api/*");
	}

	/**
	 * Initialize character encoding filter to force UTF-8 encoding in http responses (through
	 * spring MVC servlet).
	 *
	 * @param servletContext Servlet context.
	 */
	private void initCharacterEncodingFilter(ServletContext servletContext) {
		log.info("Initialize Character Encoding Filter");
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);

		FilterRegistration.Dynamic dynamicFilter = servletContext.addFilter("characterEncodingFilter", filter);
		dynamicFilter.addMappingForServletNames(DISPS, false, SPRING_SERVLET_NAME);
	}

	/**
	 * Initialize ETag Filter to add ETag header in http responses (through
	 * spring MVC servlet).
	 *
	 * @param servletContext Servlet context.
	 */
	private void initEtagFilter(ServletContext servletContext) {
		log.info("Initialize Etag Filter");
		ShallowEtagHeaderFilter filter = new ShallowEtagHeaderFilter();
		FilterRegistration.Dynamic dynamicFilter = servletContext.addFilter("etagFilter", filter);
		dynamicFilter.addMappingForServletNames(DISPS, false, SPRING_SERVLET_NAME);
	}
}
