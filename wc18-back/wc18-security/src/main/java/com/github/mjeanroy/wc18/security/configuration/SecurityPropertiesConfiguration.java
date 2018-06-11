/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.security.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.github.mjeanroy.wc18.commons.Environments.getEnvOrDefault;

@Configuration
public class SecurityPropertiesConfiguration {

	/**
	 * Class Logger.
	 */
	private static final Logger log = LoggerFactory.getLogger(SecurityPropertiesConfiguration.class);

	@Bean
	public SecurityProperties securityProperties(
			@Value("${security.headerName:X-Auth-Token}") String headerName,
			@Value("${security.secret}") String secret) {

		log.info("Creating securityProperties instance");
		String securityHeaderName = getEnvOrDefault("SECURITY_HEADER_NAME", headerName);
		String securitySecret = getEnvOrDefault("SECURITY_SECRET", secret);
		return new SecurityProperties(securityHeaderName, securitySecret);
	}
}
