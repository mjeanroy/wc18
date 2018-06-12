/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.dao;

import com.github.mjeanroy.dbunit.core.annotations.DbUnitDataSet;
import com.github.mjeanroy.dbunit.integration.spring.TransactionalDbUnitTestExecutionListener;
import com.github.mjeanroy.wc18.domain.configuration.jpa.JpaConfiguration;
import com.github.mjeanroy.wc18.domain.configuration.liquibase.LiquibaseConfiguration;
import com.github.mjeanroy.wc18.domain.tests.spring.EmbeddedDaoConfiguration;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

/**
 * Setup for repositories unit tests, using embedded database and liquibase test configuration.
 */
@DbUnitDataSet("/dbunit")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AbstractRepositoryTest.RepositoryTestConfiguration.class)
@Transactional
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
	TransactionalDbUnitTestExecutionListener.class
})
abstract class AbstractRepositoryTest {

	@Configuration
	@ComponentScan("com.github.mjeanroy.wc18.domain.dao")
	@Import({
			JpaConfiguration.class,
			LiquibaseConfiguration.class,
			EmbeddedDaoConfiguration.class
	})
	static class RepositoryTestConfiguration {
	}
}
