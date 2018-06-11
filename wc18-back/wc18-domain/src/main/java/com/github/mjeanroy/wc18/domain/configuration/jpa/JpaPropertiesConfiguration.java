/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.configuration.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.github.mjeanroy.wc18.commons.Environments.getEnvOrDefault;

@Configuration
public class JpaPropertiesConfiguration {

	/**
	 * Class Logger.
	 */
	private static final Logger log = LoggerFactory.getLogger(JpaPropertiesConfiguration.class);

	@Bean
	public JpaProperties jpaProperties(@Value("${hibernate.hbm2ddl.auto:validate}") String hbm2ddl) {
		log.info("Creating jpaProperties instance");
		String jpaHbm2DDL = getEnvOrDefault("JPA_HBM2DDL", hbm2ddl);
		return new JpaProperties(jpaHbm2DDL);
	}
}
