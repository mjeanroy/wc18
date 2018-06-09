/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.security.configuration;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SecurityPropertiesTest {

	@Test
	public void it_should_implement_equals_hash_code() {
		EqualsVerifier.forClass(SecurityProperties.class).verify();
	}

	@Test
	public void it_should_implement_to_string() {
		String headerName = "X-Auth-Token";
		String secret = "foobar";
		SecurityProperties securityProperties = new SecurityProperties(headerName, secret);

		assertThat(securityProperties.toString()).isEqualTo(
				"SecurityProperties{" +
						"headerName=X-Auth-Token, " +
						"secret=*****" +
				"}"
		);
	}
}
