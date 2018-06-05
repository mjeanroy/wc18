/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.security;

import com.github.mjeanroy.wc18.domain.models.User;
import com.github.mjeanroy.wc18.domain.services.UserService;
import com.github.mjeanroy.wc18.domain.tests.builders.UserBuilder;
import com.github.mjeanroy.wc18.security.models.Principal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PrincipalServiceImplTest {

	@Mock
	private UserService userService;

	@InjectMocks
	private PrincipalServiceImpl principalServiceImpl;

	@Test
	public void it_should_find_principal() {
		String login = "johndoe";
		createUser(login);

		Optional<Principal> principal = principalServiceImpl.findByLogin(login);

		assertThat(principal).isPresent();
		assertThat(principal.get().getLogin()).isEqualTo(login);
		verify(userService).findByLogin(login);
	}

	@Test
	public void it_should_not_find_principal_if_user_does_not_exist() {
		String login = "johndoe";
		Optional<Principal> principal = principalServiceImpl.findByLogin(login);
		assertThat(principal).isNotPresent();
	}

	private void createUser(String login) {
		User user = new UserBuilder()
			.withRandomId()
			.withLogin(login)
			.build();

		when(userService.findByLogin(login)).thenReturn(Optional.of(user));
	}
}
