/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.configuration.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.github.mjeanroy.wc18.commons.Environments.getEnvOrDefault;

@Configuration
public class DatabasePropertiesConfiguration {

	/**
	 * Class Logger.
	 */
	private static final Logger log = LoggerFactory.getLogger(DatabasePropertiesConfiguration.class);

	@Bean
	public DatabaseProperties databaseProperties(
			@Value("${database.driverClassName}") String driver,
			@Value("${database.url}") String url,
			@Value("${database.user}") String user,
			@Value("${database.password}") String password) {

		log.info("Creating database properties instance");
		String dbUrl = getEnvOrDefault("DATABASE_URL", url);
		String dbUser = getEnvOrDefault("DATABASE_USER", user);
		String dbPassword = getEnvOrDefault("DATABASE_PASSWORD", password);
		String dbDriver = getEnvOrDefault("DATABASE_DRIVER", driver);
		return new DatabaseProperties(dbUrl, dbUser, dbPassword, dbDriver);
	}
}
