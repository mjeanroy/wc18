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

package com.github.mjeanroy.wc18.security.configuration;

import com.github.mjeanroy.wc18.security.mvc.PrincipalArgumentResolver;
import com.github.mjeanroy.wc18.security.mvc.SecurityInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.inject.Inject;
import java.util.List;

@Configuration
public class SecurityWebMvcConfiguration implements WebMvcConfigurer {

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
