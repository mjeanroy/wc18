/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.configuration;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Import({
	DatabaseConfiguration.class,
	LiquibaseConfiguration.class,
	JpaConfiguration.class,
	Wc18DomainConfiguration.class
})
public @interface EnableWorldTravelersDomain {
}
