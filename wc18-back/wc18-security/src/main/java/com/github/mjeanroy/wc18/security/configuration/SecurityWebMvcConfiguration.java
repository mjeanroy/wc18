/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.security.configuration;

import com.github.mjeanroy.wc18.security.mvc.PrincipalArgumentResolver;
import com.github.mjeanroy.wc18.security.mvc.SecurityInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.inject.Inject;
import java.util.List;

@Configuration
public class SecurityWebMvcConfiguration extends WebMvcConfigurerAdapter {

	/**
	 * Class logger.
	 */
	private static final Logger log = LoggerFactory.getLogger(SecurityWebMvcConfiguration.class);

	private final SecurityInterceptor securityInterceptor;
	private final PrincipalArgumentResolver principalArgumentResolver;

	@Inject
	public SecurityWebMvcConfiguration(
			SecurityInterceptor securityInterceptor,
			PrincipalArgumentResolver principalArgumentResolver) {

		this.securityInterceptor = securityInterceptor;
		this.principalArgumentResolver = principalArgumentResolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		log.info("Add security interceptor to Spring MVC registry");
		registry.addInterceptor(securityInterceptor);
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		log.info("Add principal argument resolver to Spring MVC resolvers");
		resolvers.add(principalArgumentResolver);
	}
}
