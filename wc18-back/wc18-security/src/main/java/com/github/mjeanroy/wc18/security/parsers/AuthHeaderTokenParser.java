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

package com.github.mjeanroy.wc18.security.parsers;

import com.github.mjeanroy.wc18.security.encoders.TokenEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Implementation of {@link TokenParser} that use HTTP Request Headers
 * to read/write tokens.
 */
public class AuthHeaderTokenParser implements TokenParser {

	/**
	 * The header name.
	 */
	private final String headerName;

	/**
	 * The token encoder.
	 */
	private final TokenEncoder encoder;

	/**
	 * Create token parser.
	 *
	 * @param headerName Header name.
	 * @param encoder Token encoder.
	 */
	public AuthHeaderTokenParser(String headerName, TokenEncoder encoder) {
		this.headerName = headerName;
		this.encoder = encoder;
	}

	@Override
	public String parse(HttpServletRequest rq) {
		return Optional.ofNullable(rq.getHeader(headerName)).map(encoder::decode).orElse(null);
	}

	@Override
	public void put(HttpServletResponse rsp, String value, boolean rememberMe) {
		rsp.addHeader(headerName, encoder.encode(value));
	}

	@Override
	public void remove(HttpServletResponse rsp) {
		// Nothing to do.
	}
}
