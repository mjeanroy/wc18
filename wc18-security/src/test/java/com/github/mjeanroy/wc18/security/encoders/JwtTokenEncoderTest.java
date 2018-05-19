/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.security.encoders;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JwtTokenEncoderTest {

	private JwtTokenEncoder encoder;

	@Before
	public void setUp() {
		encoder = new JwtTokenEncoder("TUzK5gAe3BKT0i8z8W29");
	}

	@Test
	public void it_should_encode_and_decode_token() {
		String plainText = "mickael";
		String encoded = encoder.encode(plainText);
		assertThat(encoded).isNotNull().isNotEmpty().isNotEqualTo(plainText);

		String decoded = encoder.decode(encoded);
		assertThat(decoded).isEqualTo(plainText);
	}
}
