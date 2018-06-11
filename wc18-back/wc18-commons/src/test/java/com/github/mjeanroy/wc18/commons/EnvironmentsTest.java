/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.commons;

import org.assertj.core.api.Condition;
import org.junit.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class EnvironmentsTest {

	@Test
	public void it_should_get_environment_variables() {
		Set<String> someKeys = new HashSet<String>() {{
			add("USERNAME");
			add("LOGNAME");
			add("PWD");
			add("M2");
		}};

		Set<Map.Entry<String, String>> entries = System.getenv().entrySet().stream()
				.filter(e -> someKeys.contains(e.getKey()))
				.collect(Collectors.toSet());

		assertThat(entries)
				.isNotEmpty()
				.are(new Condition<Map.Entry<String, String>>() {
					@Override
					public boolean matches(Map.Entry<String, String> entry) {
						return Environments.getEnvOrDefault(entry.getKey(), "foobar").equals(entry.getValue());
					}
				});
	}

	@Test
	public void it_should_get_default_environment_variables() {
		String name = "__fake_environment_variable___";
		String value = "foobar";
		assertThat(Environments.getEnvOrDefault(name, value)).isEqualTo(value);
	}
}
