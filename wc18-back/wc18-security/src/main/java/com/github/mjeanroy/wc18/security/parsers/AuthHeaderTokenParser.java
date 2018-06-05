/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
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
