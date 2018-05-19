/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
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
