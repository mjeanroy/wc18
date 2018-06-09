/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.configuration;

import com.github.mjeanroy.wc18.domain.configuration.jpa.JpaProperties;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JpaPropertiesTest {

	@Test
	public void it_should_implement_equals_hash_code() {
		EqualsVerifier.forClass(JpaProperties.class).verify();
	}

	@Test
	public void it_should_implement_to_string() {
		String hbm2ddl = "validate";
		JpaProperties jpaProperties = new JpaProperties(hbm2ddl);
		assertThat(jpaProperties.toString()).isEqualTo(
				"JpaProperties{" +
						"hbm2ddl=validate" +
				"}"
		);
	}
}
