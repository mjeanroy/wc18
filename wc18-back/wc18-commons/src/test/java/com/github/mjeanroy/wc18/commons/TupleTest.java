/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.commons;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static com.github.mjeanroy.wc18.commons.Tuple.tuple;
import static org.assertj.core.api.Assertions.assertThat;

public class TupleTest {

	@Test
	public void it_should_create_tuple() {
		String left = "id";
		int right = 1;
		Tuple<String, Integer> tuple = tuple(left, right);
		assertThat(tuple).isNotNull();
		assertThat(tuple.getLeft()).isEqualTo(left);
		assertThat(tuple.getRight()).isEqualTo(right);
	}

	@Test
	public void it_should_implement_equals_hash_code() {
		EqualsVerifier.forClass(Tuple.class).verify();
	}

	@Test
	public void it_should_implement_to_string() {
		Tuple<String, Integer> tuple = tuple("id", 1);
		assertThat(tuple.toString()).isEqualTo(
				"Tuple{" +
						"left=id, " +
						"right=1" +
				"}"
		);
	}
}
