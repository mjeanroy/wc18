/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.configuration;

import com.github.mjeanroy.wc18.domain.configuration.db.DatabaseProperties;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DatabasePropertiesTest {

	@Test
	public void it_should_implement_equals_hash_code() {
		EqualsVerifier.forClass(DatabaseProperties.class).verify();
	}

	@Test
	public void it_should_implement_to_string() {
		String url = "jdbc:mysql://localhost:3306/test";
		String user = "root";
		String password = "password";
		String driverClassName = "com.mysql.jdbc.Driver";
		DatabaseProperties databaseProperties = new DatabaseProperties(url, user, password, driverClassName);
		assertThat(databaseProperties.toString()).isEqualTo(
				"DatabaseProperties{" +
						"url=jdbc:mysql://localhost:3306/test, " +
						"user=root, " +
						"password=******, " +
						"driverClassName=com.mysql.jdbc.Driver" +
				"}"
		);
	}
}
