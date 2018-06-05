/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.security.models;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultPrincipalTest {

	@Test
	public void it_should_create_principal_from_user() {
		String login = "john";
		Principal principal = new DefaultPrincipal(login);
		assertThat(principal.getLogin()).isEqualTo(login);
	}

	@Test
	public void it_should_implement_equals_hash_code() {
		EqualsVerifier.forClass(DefaultPrincipal.class).verify();
	}

	@Test
	public void it_should_implement_to_string() {
		Principal principal = new DefaultPrincipal("johndoe");
		assertThat(principal.toString()).isEqualTo(
			"DefaultPrincipal{" +
				"login=johndoe" +
			"}"
		);
	}
}
