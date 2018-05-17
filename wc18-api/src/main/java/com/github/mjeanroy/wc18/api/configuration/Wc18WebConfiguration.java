/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

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

	/**
	 * Class Logger.
	 */
	private static final Logger log = LoggerFactory.getLogger(Wc18WebConfiguration.class);

	@PostConstruct
	public void setUp() {
		log.info("Force default time-zone to UTC");
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
}
