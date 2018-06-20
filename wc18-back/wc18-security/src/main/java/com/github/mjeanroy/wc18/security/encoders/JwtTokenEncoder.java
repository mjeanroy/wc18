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

package com.github.mjeanroy.wc18.security.encoders;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

public class JwtTokenEncoder implements TokenEncoder {

	private static final String ISSUER = "wc18";
	private static final String CLAIM_ID = "wc18-value";

	/**
	 * The JWT Token Secret.
	 */
	private final byte[] secret;

	public JwtTokenEncoder(String secret) {
		this.secret = secret.getBytes(StandardCharsets.UTF_8);
	}

	@Override
	public String encode(String decodedValue) {
		return JWT.create()
			.withIssuer(ISSUER)
			.withIssuedAt(new Date())
			.withExpiresAt(Date.from(LocalDate.now().plusYears(1).atStartOfDay().toInstant(ZoneOffset.UTC)))
			.withClaim(CLAIM_ID, decodedValue)
			.sign(getAlgorithm());
	}

	@Override
	public String decode(String encodedValue) {
		JWTVerifier verifier = JWT.require(getAlgorithm())
			.withIssuer(ISSUER)
			.build();

		return verifier.verify(encodedValue).getClaim(CLAIM_ID).asString();
	}

	private Algorithm getAlgorithm() {
		return Algorithm.HMAC512(secret);
	}
}
