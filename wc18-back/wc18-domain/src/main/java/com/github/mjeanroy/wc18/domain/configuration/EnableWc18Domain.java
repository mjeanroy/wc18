/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.configuration;

import com.github.mjeanroy.wc18.domain.configuration.db.DatabaseConfiguration;
import com.github.mjeanroy.wc18.domain.configuration.db.DatabasePropertiesConfiguration;
import com.github.mjeanroy.wc18.domain.configuration.jpa.JpaConfiguration;
import com.github.mjeanroy.wc18.domain.configuration.jpa.JpaPropertiesConfiguration;
import com.github.mjeanroy.wc18.domain.configuration.liquibase.LiquibaseConfiguration;
import com.github.mjeanroy.wc18.domain.configuration.liquibase.LiquibasePropertiesConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Import({
		DatabasePropertiesConfiguration.class,
		DatabaseConfiguration.class,

		LiquibasePropertiesConfiguration.class,
		LiquibaseConfiguration.class,

		JpaPropertiesConfiguration.class,
		JpaConfiguration.class,

		Wc18DomainConfiguration.class
})
public @interface EnableWc18Domain {
}
