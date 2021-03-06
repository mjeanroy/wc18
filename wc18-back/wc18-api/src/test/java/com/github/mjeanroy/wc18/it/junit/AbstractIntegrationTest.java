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
import com.github.mjeanroy.junit.servers.client.HttpClient;
import com.github.mjeanroy.junit.servers.client.HttpResponse;
import com.github.mjeanroy.junit.servers.jetty.EmbeddedJettyConfiguration;
import com.github.mjeanroy.junit.servers.jetty.jupiter.JettyServerExtension;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.net.URL;

import static com.github.mjeanroy.wc18.it.json.JsonBuilders.jsonObject;

@DbUnitDataSet("/dbunit")
@DbUnitSetup(DbUnitOperation.CLEAN_INSERT)
public abstract class AbstractIntegrationTest {

	private static final SpringContextHook SPRING = SpringContextHook.getInstance();

	private static final EmbeddedJettyConfiguration SERVER_CONFIGURATION = EmbeddedJettyConfiguration.builder()
		.withHook(TimeZoneContextHook.get())
		.withHook(SPRING)
		.build();

	@RegisterExtension
	static JettyServerExtension server = new JettyServerExtension(SERVER_CONFIGURATION);

	@RegisterExtension
	static DbUnitSpringExtension dbUnit = new DbUnitSpringExtension(SPRING);

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
	 * Get token of admin user.
	 *
	 * @return The token value.
	 */
	protected String getAdminToken(HttpClient client) {
		HttpResponse response = client.preparePost("/api/login")
			.setBody(jsonObject()
				.add("login", "mickael")
				.add("password", "azerty123")
				.serialize())
			.executeJson();

		return response.getHeader("X-Auth-Token").getFirstValue();
	}
}
