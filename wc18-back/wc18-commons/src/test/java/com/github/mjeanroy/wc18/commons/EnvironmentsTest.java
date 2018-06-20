/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Mickael Jeanroy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
