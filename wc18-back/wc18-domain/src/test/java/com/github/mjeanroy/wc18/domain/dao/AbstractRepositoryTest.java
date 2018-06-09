/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.dao;

import com.github.mjeanroy.dbunit.core.annotations.DbUnitDataSet;
import com.github.mjeanroy.dbunit.integration.spring.TransactionalDbUnitTestExecutionListener;
import com.github.mjeanroy.wc18.domain.configuration.jpa.JpaConfiguration;
import com.github.mjeanroy.wc18.domain.configuration.jpa.JpaProperties;
import com.github.mjeanroy.wc18.domain.configuration.liquibase.LiquibaseConfiguration;
import com.github.mjeanroy.wc18.domain.configuration.liquibase.LiquibaseProperties;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

/**
 * Setup for repositories unit tests, using embedded database and liquibase test configuration.
 */
@DbUnitDataSet("/dbunit")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AbstractRepositoryTest.RepositoryTestConfiguration.class)
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
	TransactionalDbUnitTestExecutionListener.class
})

@Transactional
abstract class AbstractRepositoryTest {

	@Configuration
	@ComponentScan("com.github.mjeanroy.wc18.domain.dao")
	@Import({
		JpaConfiguration.class,
		LiquibaseConfiguration.class
	})
	static class RepositoryTestConfiguration {
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
}
