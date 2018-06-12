/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.tests.spring;

import com.github.mjeanroy.wc18.domain.configuration.jpa.JpaConfiguration;
import com.github.mjeanroy.wc18.domain.configuration.jpa.JpaProperties;
import com.github.mjeanroy.wc18.domain.configuration.liquibase.LiquibaseConfiguration;
import com.github.mjeanroy.wc18.domain.configuration.liquibase.LiquibaseProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@Import({
		JpaConfiguration.class,
		LiquibaseConfiguration.class
})
public class EmbeddedDaoConfiguration {
	@Bean
	public DataSource datasource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).build();
	}

	@Bean
	public JpaProperties jpaProperties() {
		return new JpaProperties("validate");
	}

	@Bean
	public LiquibaseProperties liquibaseProperties() {
		boolean dropFirst = false;
		boolean shouldRun = true;
		String contexts = "schema";
		String changeLog = "/db/changelogs/db-changelogs.xml";
		return new LiquibaseProperties(changeLog, shouldRun, dropFirst, contexts);
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);
		return jdbcTemplate;
	}
}
