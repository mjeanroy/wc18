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

import com.github.mjeanroy.junit.servers.servers.EmbeddedServer;
import com.github.mjeanroy.junit.servers.servers.Hook;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

public class SpringContextHook implements Hook {

	/**
	 * Singleton instance.
	 */
	private static final SpringContextHook INSTANCE = new SpringContextHook();

	/**
	 * Get hook instance.
	 *
	 * @return Hook instance.
	 */
	public static SpringContextHook getInstance() {
		return INSTANCE;
	}

	/**
	 * Application datasource created within embedded
	 * container.
	 */
	private DataSource dataSource;

	// Ensure non instantiation.
	private SpringContextHook() {
	}

	@Override
	public void pre(EmbeddedServer server) {
	}

	@Override
	public void post(EmbeddedServer server) {
		dataSource = null;
	}

	@Override
	public void onStarted(EmbeddedServer server, ServletContext servletContext) {
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		dataSource = webApplicationContext.getBean(DataSource.class);
	}

	/**
	 * Get the configured {@link DataSource} configured in spring application.
	 *
	 * @return The {@link DataSource}.
	 */
	DataSource getDataSource() {
		return dataSource;
	}
}
