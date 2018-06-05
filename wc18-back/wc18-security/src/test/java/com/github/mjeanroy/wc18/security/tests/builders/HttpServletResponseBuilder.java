/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.security.tests.builders;

import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;

/**
 * Builder for {@link HttpServletResponse} instances.
 */
public class HttpServletResponseBuilder {

	/**
	 * Build final {@link HttpServletResponse} instance.
	 *
	 * @return The final instance.
	 */
	public HttpServletResponse build() {
		return new MockHttpServletResponse();
	}
}
