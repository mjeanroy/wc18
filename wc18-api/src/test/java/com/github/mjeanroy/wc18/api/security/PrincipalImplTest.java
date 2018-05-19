/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.security;

import com.github.mjeanroy.wc18.domain.models.User;
import com.github.mjeanroy.wc18.domain.tests.builders.UserBuilder;
import com.github.mjeanroy.wc18.security.models.Principal;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PrincipalImplTest {

	@Test
	public void it_should_create_principal_from_user() {
		String login = "john";
		User user = new UserBuilder()
			.withRandomId()
			.withLogin(login)
			.build();

		Principal principal = new PrincipalImpl(user);

		assertThat(principal.getLogin()).isEqualTo(login);
	}
}
