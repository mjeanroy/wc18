/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.tests.builders;

import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Builder for {@link HttpServletRequest}.
 */
public class HttpServletRequestBuilder {

	/**
	 * Create final {@link HttpServletRequest} instance.
	 *
	 * @return The final instance.
	 */
	public HttpServletRequest build() {
		return new MockHttpServletRequest();
	}
}
