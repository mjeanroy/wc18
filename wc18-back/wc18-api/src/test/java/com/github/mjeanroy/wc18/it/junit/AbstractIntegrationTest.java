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

package com.github.mjeanroy.wc18.it.junit;

import com.github.mjeanroy.dbunit.core.annotations.DbUnitDataSet;
import com.github.mjeanroy.dbunit.core.annotations.DbUnitSetup;
import com.github.mjeanroy.dbunit.core.operation.DbUnitOperation;
import com.github.mjeanroy.dbunit.integration.junit.DbUnitRule;
import com.github.mjeanroy.junit.servers.client.HttpClient;
import com.github.mjeanroy.junit.servers.jetty.EmbeddedJettyConfiguration;
import com.github.mjeanroy.junit.servers.rules.JettyServerRule;
import com.github.mjeanroy.junit.servers.rules.ServerRule;
import org.junit.ClassRule;
import org.junit.Rule;

import java.net.URL;

@DbUnitDataSet("/dbunit")
@DbUnitSetup(DbUnitOperation.CLEAN_INSERT)
public abstract class AbstractIntegrationTest {

	private static final SpringContextHook SPRING = SpringContextHook.getInstance();

	private static final EmbeddedJettyConfiguration SERVER_CONFIGURATION = EmbeddedJettyConfiguration.builder()
		.withHook(SPRING)
		.build();

	@ClassRule
	public static ServerRule server = new JettyServerRule(SERVER_CONFIGURATION);

	@Rule
	public DbUnitRule rule = new DbUnitRuleIntegrationTest(SPRING);

	/**
	 * Get JSON file from classpath.
	 *
	 * @param name File name.
	 * @return The file URL.
	 */
	protected URL getJsonFile(String name) {
		return getClass().getResource("/it/" + name);
	}

	/**
	 * Get HTTP client to request started server.
	 *
	 * @return The HTTP client.
	 */
	protected HttpClient getClient() {
		return server.getClient();
	}
}
