/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Mickael Jeanroy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
	 * The junit dispatcher servlet name.
	 */
	private static final String SPRING_SERVLET_NAME = "junit";

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
	 * Initialize junit context and junit mvc dispatcher servlet.
	 *
	 * @param servletContext Servlet context.
	 */
	private void initSpring(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext context = initContext(servletContext);
		initSpringMvc(servletContext, context);
	}

	/**
	 * Initialize junit context.
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
	 * Initialize junit MVC dispatcher servlet.
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
	 * junit MVC servlet).
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
	 * junit MVC servlet).
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
