/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Static Environment Utilities.
 */
public final class EnvironmentUtils {

	/**
	 * Class Logger.
	 */
	private static final Logger log = LoggerFactory.getLogger(EnvironmentUtils.class);

	// Ensure non instantiation.
	private EnvironmentUtils() {
	}

	/**
	 * Get system environment value, or default to given one.
	 *
	 * @param name System property name.
	 * @param defaultValue The default value.
	 * @return The final value.
	 */
	public static String getEnvOrDefault(String name, String defaultValue) {
		log.debug("Get '{}' environment variable", name);

		String value = System.getenv(name);
		if (value == null) {
			log.debug("Environment variable '{}' not set, use default value", name);
			return defaultValue;
		}

		return value;
	}
}
