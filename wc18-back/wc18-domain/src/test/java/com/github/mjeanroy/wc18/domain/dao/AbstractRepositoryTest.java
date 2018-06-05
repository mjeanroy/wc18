/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.dao;

import com.github.mjeanroy.dbunit.core.annotations.DbUnitDataSet;
import com.github.mjeanroy.dbunit.integration.spring.DbUnitTestExecutionListener;
import com.github.mjeanroy.dbunit.integration.spring.TransactionalDbUnitTestExecutionListener;
import com.github.mjeanroy.wc18.domain.configuration.JpaConfiguration;
import com.github.mjeanroy.wc18.domain.configuration.LiquibaseConfiguration;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
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
	@PropertySource("classpath:liquibase-test.properties")
	@Import({
		JpaConfiguration.class,
		LiquibaseConfiguration.class
	})
	static class RepositoryTestConfiguration {
		@Bean
		public DataSource datasource() {
			// Note that we can't use H2 database because of an incompatibility in UUID column type
			// between Hibernate & H2
			// See: https://hibernate.atlassian.net/browse/HHH-9835
			return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).build();
		}

		@Bean
		public JdbcTemplate jdbcTemplate(DataSource dataSource) {
			JdbcTemplate jdbcTemplate = new JdbcTemplate();
			jdbcTemplate.setDataSource(dataSource);
			return jdbcTemplate;
		}
	}
}
