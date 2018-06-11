/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.commons;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MoreStringsTest {

	@Test
	public void it_should_check_if_string_is_empty() {
		assertThat(MoreStrings.isEmpty(null)).isTrue();
		assertThat(MoreStrings.isEmpty("")).isTrue();
		assertThat(MoreStrings.isEmpty("  ")).isFalse();
		assertThat(MoreStrings.isEmpty("foobar")).isFalse();
	}
}
