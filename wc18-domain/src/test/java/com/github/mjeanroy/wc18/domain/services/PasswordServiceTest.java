/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.services;

import org.junit.Test;
import org.mockito.InjectMocks;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordServiceTest extends AbstractServiceTest {

	@InjectMocks
	private PasswordService passwordService;

	@Test
	public void it_should_encode_password() {
		String plainText = "azerty123";
		String hash = passwordService.encode(plainText);
		assertThat(hash).isNotNull().isNotEmpty();
	}

	@Test
	public void it_should_check_if_two_passwords_match() {
		String plainText = "azerty123";
		String hash = "$2a$10$fS8jhRrtBR.9W9CqEr.Mk.6igXsC6iuPTaW.bXe.L0VANTyOwvL3e";
		assertThat(passwordService.match(plainText, hash)).isTrue();
	}
}
