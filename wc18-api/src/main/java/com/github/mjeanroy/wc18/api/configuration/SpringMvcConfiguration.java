/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import javax.inject.Inject;
import java.util.List;

@Configuration
@EnableWebMvc
@Import({
	BeanValidationConfiguration.class,
	JacksonConfiguration.class
})
public class SpringMvcConfiguration implements WebMvcConfigurer {

	/**
	 * Class Logger.
	 */
	private static final Logger log = LoggerFactory.getLogger(SpringMvcConfiguration.class);

	private final ObjectMapper objectMapper;

	@Inject
	public SpringMvcConfiguration(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		log.info("Configuring path matching:");
		log.debug("- Use full path: true");
		log.debug("- Use suffix pattern match: false");
		configurer.setUrlPathHelper(urlPathHelper());
		configurer.setUseSuffixPatternMatch(false);
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		customizeJacksonObjectMapper(converters);
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		customizeJacksonObjectMapper(converters);
	}

	private UrlPathHelper urlPathHelper() {
		UrlPathHelper urlPathHelper = new UrlPathHelper();
		urlPathHelper.setAlwaysUseFullPath(true);
		return urlPathHelper;
	}

	private void customizeJacksonObjectMapper(List<HttpMessageConverter<?>> converters) {
		for (HttpMessageConverter<?> messageConverter : converters) {
			if (messageConverter instanceof MappingJackson2HttpMessageConverter) {
				log.debug("Customize Jackson MessageConverter with application object mapper");
				((MappingJackson2HttpMessageConverter) messageConverter).setObjectMapper(objectMapper);
			}
		}
	}
}
