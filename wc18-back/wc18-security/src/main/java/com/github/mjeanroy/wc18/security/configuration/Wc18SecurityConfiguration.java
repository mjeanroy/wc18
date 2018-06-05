/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Wc18SecurityConfiguration {

	/**
	 * Class logger.
	 */
	private static final Logger log = LoggerFactory.getLogger(Wc18SecurityConfiguration.class);

	/**
	 * The header name to use to read/write token value.
	 */
	private final String headerName;

	/**
	 * The internal secret key.
	 */
	private final String secret;

	public Wc18SecurityConfiguration(
			@Value("${security.headerName:X-Auth-Token}") String headerName,
			@Value("${security.secret}") String secret) {

		this.headerName = headerName;
		this.secret = secret;
	}

	@Bean
	public TokenEncoder jwtTokenEncoder() {
		log.info("Creating JWT Token Encoder");
		return new JwtTokenEncoder(secret);
	}

	@Bean
	public SecurityService securityService(TokenParser tokenParser, PrincipalService principalService) {
		log.info("Creating security service");
		return new SecurityService(tokenParser, principalService);
	}

	@Bean
	public TokenParser tokenParser(TokenEncoder tokenEncoder) {
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
