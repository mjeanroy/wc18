/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.security.service;

import com.github.mjeanroy.wc18.security.models.Principal;
import com.github.mjeanroy.wc18.security.parsers.TokenParser;
import com.github.mjeanroy.wc18.security.tests.builders.HttpServletRequestBuilder;
import com.github.mjeanroy.wc18.security.tests.builders.HttpServletResponseBuilder;
import com.github.mjeanroy.wc18.security.tests.builders.PrincipalBuilder;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class SecurityServiceTest {

	private PrincipalService principalService;
	private TokenParser parser;
	private SecurityService securityService;

	@Before
	public void setUp() {
		principalService = mock(PrincipalService.class);
		parser = mock(TokenParser.class);
		securityService = new SecurityService(parser, principalService);

		initPrincipalService();
	}

	private void initPrincipalService() {
		when(principalService.findByLogin(anyString())).thenReturn(Optional.empty());
	}

	@Test
	public void it_should_authenticate() {
		HttpServletResponse rsp = new HttpServletResponseBuilder().build();
		String value = "test";
		Principal principal = createPrincipal(value);
		securityService.authenticate(rsp, principal);
		verify(parser).put(rsp, value, false);
	}

	@Test
	public void it_should_logout() {
		HttpServletResponse rsp = new HttpServletResponseBuilder().build();
		securityService.logout(rsp);
		verify(parser).remove(rsp);
	}

	@Test
	public void it_get_principal() {
		HttpServletRequest rq = new HttpServletRequestBuilder().build();
		String value = "test";
		Principal principal = createPrincipal(value);
		when(parser.parse(rq)).thenReturn(value);

		Optional<Principal> result = securityService.getPrincipal(rq);

		assertThat(result).isPresent().hasValue(principal);
	}

	@Test
	public void it_return_empty_principal_without_token() {
		HttpServletRequest rq = new HttpServletRequestBuilder().build();
		when(parser.parse(rq)).thenReturn(null);

		Optional<Principal> result = securityService.getPrincipal(rq);

		assertThat(result).isEmpty();
		verifyZeroInteractions(principalService);
	}

	private Principal createPrincipal(String login) {
		Principal principal = new PrincipalBuilder()
			.withLogin(login)
			.build();

		when(principalService.findByLogin(login)).thenReturn(Optional.of(principal));
		return principal;
	}
}
