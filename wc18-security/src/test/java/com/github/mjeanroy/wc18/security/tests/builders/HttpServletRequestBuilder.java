/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.security.tests.builders;

import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Builder for {@link HttpServletRequest} instances.
 */
public class HttpServletRequestBuilder {

	/**
	 * The request method.
	 */
	private String method;

	/**
	 * The list of headers.
	 */
	private final Map<String, String> headers;

	/**
	 * Create the default builder.
	 */
	public HttpServletRequestBuilder() {
		this.method = "GET";
		this.headers = new LinkedHashMap<>();
	}

	/**
	 * Add new header.
	 *
	 * @param name Header name.
	 * @param value Header value.
	 * @return The current build.
	 */
	public HttpServletRequestBuilder withHeader(String name, String value) {
		this.headers.put(name, value);
		return this;
	}

	/**
	 * Set {@link #method}
	 *
	 * @param method New {@link #method}
	 * @return The current builder.
	 */
	public HttpServletRequestBuilder withMethod(String method) {
		this.method = method;
		return this;
	}

	/**
	 * Build final {@link HttpServletRequest} instance.
	 *
	 * @return The final instance.
	 */
	public HttpServletRequest build() {
		MockHttpServletRequest rq = new MockHttpServletRequest();
		rq.setMethod(method);

		for (Map.Entry<String, String> entry : headers.entrySet()) {
			rq.addHeader(entry.getKey(), entry.getValue());
		}

		return rq;
	}
}
