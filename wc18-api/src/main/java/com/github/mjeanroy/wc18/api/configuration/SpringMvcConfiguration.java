/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class SpringMvcConfiguration extends WebMvcConfigurationSupport {

	/**
	 * Class Logger.
	 */
	private static final Logger log = LoggerFactory.getLogger(SpringMvcConfiguration.class);

	@Bean
	@Override
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		log.info("Create request handler mapping");
		log.debug("- Use full path: true");
		log.debug("- Use suffix pattern match: false");

		RequestMappingHandlerMapping handlerMapping = super.requestMappingHandlerMapping();
		handlerMapping.setAlwaysUseFullPath(true);
		handlerMapping.setUseSuffixPatternMatch(false);

		return handlerMapping;
	}
}
