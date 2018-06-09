/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.configuration.liquibase;

import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfiguration {

	/**
	 * Class Logger.
	 */
	private static final Logger log = LoggerFactory.getLogger(LiquibaseConfiguration.class);

	/**
	 * The bean name.
	 * Note that this bean <strong>must</strong> be created before JPA entity manager
	 * as hibernate validate schema on startup, so liquibase migration have to be executed.
	 */
	public static final String LIQUIBASE_BEAN_NAME = "liquibase";

	@Bean(name = LIQUIBASE_BEAN_NAME)
	public SpringLiquibase liquibase(LiquibaseProperties liquibaseProperties, DataSource dataSource) {
		log.info("Creating liquibase with properties: {}", liquibaseProperties);

		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setBeanName(LIQUIBASE_BEAN_NAME);
		liquibase.setDataSource(dataSource);
		liquibase.setDropFirst(liquibaseProperties.isDropFirst());
		liquibase.setContexts(liquibaseProperties.getContexts());
		liquibase.setShouldRun(liquibaseProperties.isShouldRun());
		liquibase.setChangeLog(liquibaseProperties.getChangeLog());
		return liquibase;
	}
}
