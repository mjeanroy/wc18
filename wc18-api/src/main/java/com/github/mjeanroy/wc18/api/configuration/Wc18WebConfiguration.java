/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import({
	SpringMvcConfiguration.class,
	Wc18ApiConfiguration.class
})
@PropertySource({
		"classpath:database.properties",
		"classpath:liquibase.properties",
		"classpath:hibernate.properties"
})
public class Wc18WebConfiguration {
}
