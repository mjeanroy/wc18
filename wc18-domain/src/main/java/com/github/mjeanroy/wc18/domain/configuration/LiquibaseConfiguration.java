/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
	static final String LIQUIBASE_BEAN_NAME = "liquibase";

	/**
	 * The liquibase {@code "dropFirst"} property.
	 * @see SpringLiquibase#dropFirst
	 */
	private final boolean dropFirst;

	/**
	 * The liquibase {@code "shouldRun"} property.
	 * @see SpringLiquibase#shouldRun
	 */
	private final boolean shouldRun;

	/**
	 * The liquibase {@code "contexts"} property.
	 * @see SpringLiquibase#contexts
	 */
	private final String contexts;

	/**
	 * The liquibase {@code "changeLog"} property.
	 * @see SpringLiquibase#changeLog
	 */
	private final String changeLog;

	public LiquibaseConfiguration(
			@Value("${liquibase.dropFirst:false}") boolean dropFirst,
			@Value("${liquibase.shouldRun:true}") boolean shouldRun,
			@Value("${liquibase.contexts:}") String contexts,
			@Value("${liquibase.changeLog:classpath:/db/changelogs/db-changelogs.xml}") String changeLog) {

		this.dropFirst = dropFirst;
		this.shouldRun = shouldRun;
		this.contexts = contexts;
		this.changeLog = changeLog;
	}

	@Bean(name = LIQUIBASE_BEAN_NAME)
	public SpringLiquibase liquibase(DataSource dataSource) {
		log.info("Creating liquibase");
		log.debug(" - dropFirst: {}", dropFirst);
		log.debug(" - shouldRun: {}", shouldRun);
		log.debug(" - changeLog: {}", changeLog);
		log.debug(" - contexts: {}", contexts);
		log.debug(" - dataSource: {}", dataSource);

		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setBeanName(LIQUIBASE_BEAN_NAME);
		liquibase.setDataSource(dataSource);
		liquibase.setDropFirst(dropFirst);
		liquibase.setContexts(contexts);
		liquibase.setShouldRun(shouldRun);
		liquibase.setChangeLog(changeLog);
		return liquibase;
	}
}
