package com.github.mjeanroy.wc18.domain.tests.junit;

import com.github.mjeanroy.wc18.domain.configuration.jpa.JpaConfiguration;
import com.github.mjeanroy.wc18.domain.configuration.liquibase.LiquibaseConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.github.mjeanroy.wc18.domain.dao")
@Import({
	JpaConfiguration.class,
	LiquibaseConfiguration.class,
	EmbeddedDaoConfiguration.class
})
class RepositoryTestConfiguration {
}
