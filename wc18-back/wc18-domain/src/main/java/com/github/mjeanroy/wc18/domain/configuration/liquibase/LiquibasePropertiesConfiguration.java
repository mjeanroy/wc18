/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.configuration.liquibase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.github.mjeanroy.wc18.domain.configuration.EnvironmentUtils.getEnvOrDefault;

@Configuration
public class LiquibasePropertiesConfiguration {

	/**
	 * Class Logger.
	 */
	private static final Logger log = LoggerFactory.getLogger(LiquibasePropertiesConfiguration.class);

	@Bean
	public LiquibaseProperties liquibaseProperties(
			@Value("${liquibase.dropFirst:false}") boolean dropFirst,
			@Value("${liquibase.shouldRun:true}") boolean shouldRun,
			@Value("${liquibase.contexts:}") String contexts,
			@Value("${liquibase.changeLog:classpath:/db/changelogs/db-changelogs.xml}") String changeLog) {

		log.info("Creating liquibaseProperties instance");
		String liquibaseChangelog = getEnvOrDefault("LIQUIBASE_CHANGELOG", changeLog);
		Boolean liquibaseShouldRun = Boolean.valueOf(getEnvOrDefault("LIQUIBASE_SHOULD_RUN", Boolean.toString(shouldRun)));
		Boolean liquibaseDropFirst = Boolean.valueOf(getEnvOrDefault("LIQUIBASE_DROP_FIRST", Boolean.toString(dropFirst)));
		String liquibaseContexts = getEnvOrDefault("LIQUIBASE_CONTEXTS", contexts);
		return new LiquibaseProperties(liquibaseChangelog, liquibaseShouldRun, liquibaseDropFirst, liquibaseContexts);
	}
}
