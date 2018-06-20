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

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
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
