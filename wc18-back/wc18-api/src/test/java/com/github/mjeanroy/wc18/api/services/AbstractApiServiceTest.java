/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.services;

import com.github.mjeanroy.dbunit.core.annotations.DbUnitDataSet;
import com.github.mjeanroy.dbunit.integration.spring.TransactionalDbUnitTestExecutionListener;
import com.github.mjeanroy.spring.mappers.configuration.EnableMapper;
import com.github.mjeanroy.wc18.domain.configuration.jpa.JpaConfiguration;
import com.github.mjeanroy.wc18.domain.configuration.liquibase.LiquibaseConfiguration;
import com.github.mjeanroy.wc18.domain.tests.spring.EmbeddedDaoConfiguration;
import com.github.mjeanroy.wc18.security.service.SecurityService;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.mock;

@DbUnitDataSet("/dbunit")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AbstractApiServiceTest.ApiServiceTestConfiguration.class)
@Transactional
@TestExecutionListeners({
		DependencyInjectionTestExecutionListener.class,
		TransactionalDbUnitTestExecutionListener.class
})
public abstract class AbstractApiServiceTest {

	@Configuration
	@EnableMapper
	@ComponentScan({
			"com.github.mjeanroy.wc18.api.mappers",
			"com.github.mjeanroy.wc18.api.services",
			"com.github.mjeanroy.wc18.domain.services",
			"com.github.mjeanroy.wc18.domain.dao"
	})
	@Import({
			JpaConfiguration.class,
			LiquibaseConfiguration.class,
			EmbeddedDaoConfiguration.class
	})
	static class ApiServiceTestConfiguration {

		@Bean
		public SecurityService securityService() {
			return mock(SecurityService.class);
		}
	}
}
