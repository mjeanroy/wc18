/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.configuration;

import com.github.mjeanroy.wc18.domain.configuration.liquibase.LiquibaseProperties;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LiquibasePropertiesTest {

	@Test
	public void it_should_implement_equals_hash_code() {
		EqualsVerifier.forClass(LiquibaseProperties.class).verify();
	}

	@Test
	public void it_should_implement_to_string() {
		String changeLog = "classpath:liquibase.xml";
		boolean shouldRun = true;
		boolean dropFirst = true;
		String contexts = "test";
		LiquibaseProperties liquibaseProperties = new LiquibaseProperties(changeLog, shouldRun, dropFirst, contexts);

		assertThat(liquibaseProperties.toString()).isEqualTo(
				"LiquibaseProperties{" +
						"changeLog=classpath:liquibase.xml, " +
						"shouldRun=true, " +
						"dropFirst=true, " +
						"contexts=test" +
				"}"
		);
	}
}
