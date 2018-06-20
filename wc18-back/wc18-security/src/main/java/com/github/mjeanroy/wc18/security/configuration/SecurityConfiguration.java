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

import com.github.mjeanroy.wc18.security.encoders.JwtTokenEncoder;
import com.github.mjeanroy.wc18.security.encoders.TokenEncoder;
import com.github.mjeanroy.wc18.security.mvc.PrincipalArgumentResolver;
import com.github.mjeanroy.wc18.security.mvc.SecurityInterceptor;
import com.github.mjeanroy.wc18.security.parsers.AuthHeaderTokenParser;
import com.github.mjeanroy.wc18.security.parsers.TokenParser;
import com.github.mjeanroy.wc18.security.service.PrincipalService;
import com.github.mjeanroy.wc18.security.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfiguration {

	/**
	 * Class Logger.
	 */
	private static final Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);

	@Bean
	public TokenEncoder jwtTokenEncoder(SecurityProperties securityProperties) {
		log.info("Creating JWT Token Encoder");
		return new JwtTokenEncoder(securityProperties.getSecret());
	}

	@Bean
	public SecurityService securityService(TokenParser tokenParser, PrincipalService principalService) {
		log.info("Creating security service");
		return new SecurityService(tokenParser, principalService);
	}

	@Bean
	public TokenParser tokenParser(SecurityProperties securityProperties, TokenEncoder tokenEncoder) {
		String headerName = securityProperties.getHeaderName();
		log.info("Creating security token parser with header name: {}", headerName);
		return new AuthHeaderTokenParser(headerName, tokenEncoder);
	}

	@Bean
	public SecurityInterceptor securityInterceptor(SecurityService securityService) {
		log.info("Creating security interceptor");
		return new SecurityInterceptor(securityService);
	}

	@Bean
	public PrincipalArgumentResolver principalArgumentResolver(SecurityService securityService) {
		log.info("Creating principal argument resolver");
		return new PrincipalArgumentResolver(securityService);
	}
}
