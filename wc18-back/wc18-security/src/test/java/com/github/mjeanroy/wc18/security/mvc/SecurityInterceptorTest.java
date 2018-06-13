/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.security.mvc;

import com.github.mjeanroy.wc18.security.Security;
import com.github.mjeanroy.wc18.security.models.Principal;
import com.github.mjeanroy.wc18.security.service.SecurityService;
import com.github.mjeanroy.wc18.security.tests.builders.HttpServletRequestBuilder;
import com.github.mjeanroy.wc18.security.tests.builders.HttpServletResponseBuilder;
import com.github.mjeanroy.wc18.security.tests.builders.PrincipalBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SecurityInterceptorTest {

	private SecurityService securityService;
	private SecurityInterceptor securityInterceptor;

	@Before
	public void setUp() {
		securityService = mock(SecurityService.class);
		securityInterceptor = new SecurityInterceptor(securityService);

		initSecurityService();
	}

	private void initSecurityService() {
		when(securityService.getPrincipal(any(HttpServletRequest.class))).thenReturn(Optional.empty());
	}

	@Test
	public void it_should_stop_authenticated_GET_request_without_authentication() {
		doTestWithHttpMethod("GET");
	}

	@Test
	public void it_should_stop_authenticated_GET_request_with_required_role() {
		doTestWithHttpMethodAndRequiredRole("GET");
	}

	@Test
	public void it_should_stop_authenticated_POST_request_without_authentication() {
		doTestWithHttpMethod("POST");
	}

	@Test
	public void it_should_stop_authenticated_POST_request_with_required_role() {
		doTestWithHttpMethodAndRequiredRole("POST");
	}

	@Test
	public void it_should_stop_authenticated_PUT_request_without_authentication() {
		doTestWithHttpMethod("PUT");
	}

	@Test
	public void it_should_stop_authenticated_PUT_request_with_required_role() {
		doTestWithHttpMethodAndRequiredRole("PUT");
	}

	@Test
	public void it_should_stop_authenticated_PATCH_request_without_authentication() {
		doTestWithHttpMethod("PATCH");
	}

	@Test
	public void it_should_stop_authenticated_PATCH_request_with_required_role() {
		doTestWithHttpMethodAndRequiredRole("PATCH");
	}

	@Test
	public void it_should_stop_authenticated_DELETE_request_without_authentication() {
		doTestWithHttpMethod("DELETE");
	}

	@Test
	public void it_should_stop_authenticated_DELETE_request_with_required_role() {
		doTestWithHttpMethodAndRequiredRole("DELETE");
	}

	private void doTestWithHttpMethod(String method) {
		HttpServletRequest rq = new HttpServletRequestBuilder().withMethod(method).build();
		HttpServletResponse rsp = new HttpServletResponseBuilder().build();
		HandlerMethod handlerMethod = getHandlerMethod("authenticatedMethod");
		boolean result = securityInterceptor.preHandle(rq, rsp, handlerMethod);

		assertThat(rsp.getStatus()).isEqualTo(401);
		assertThat(result).isFalse();
	}

	private void doTestWithHttpMethodAndRequiredRole(String method) {
		String login = "test";
		String role = "USER";

		HttpServletRequest rq = new HttpServletRequestBuilder().withMethod(method).build();
		HttpServletResponse rsp = new HttpServletResponseBuilder().build();
		HandlerMethod handlerMethod = getHandlerMethod("authenticatedMethodWithRole");
		Principal principal = createPrincipal(login, role);
		when(securityService.getPrincipal(rq)).thenReturn(Optional.of(principal));

		boolean result = securityInterceptor.preHandle(rq, rsp, handlerMethod);

		assertThat(rsp.getStatus()).isEqualTo(403);
		assertThat(result).isFalse();
	}

	@Test
	public void it_should_not_stop_authenticated_request_with_authentication() {
		String login = "test";
		String role = "USER";

		HttpServletRequest rq = new HttpServletRequestBuilder().withMethod("GET").build();
		HttpServletResponse rsp = new HttpServletResponseBuilder().build();
		HandlerMethod handlerMethod = getHandlerMethod("authenticatedMethod");
		Principal principal = createPrincipal(login, role);
		when(securityService.getPrincipal(rq)).thenReturn(Optional.of(principal));

		boolean result = securityInterceptor.preHandle(rq, rsp, handlerMethod);

		assertThat(rsp.getStatus()).isNotEqualTo(401).isNotEqualTo(403);
		assertThat(result).isTrue();
	}

	@Test
	public void it_should_not_stop_authenticated_request_with_authentication_and_required_role() {
		String login = "test";
		String role = "ADMIN";

		HttpServletRequest rq = new HttpServletRequestBuilder().withMethod("GET").build();
		HttpServletResponse rsp = new HttpServletResponseBuilder().build();
		HandlerMethod handlerMethod = getHandlerMethod("authenticatedMethodWithRole");

		Principal principal = createPrincipal(login, role);
		when(securityService.getPrincipal(rq)).thenReturn(Optional.of(principal));

		boolean result = securityInterceptor.preHandle(rq, rsp, handlerMethod);

		assertThat(rsp.getStatus()).isNotEqualTo(401).isNotEqualTo(403);
		assertThat(result).isTrue();
	}

	@Test
	public void it_should_not_stop_non_authenticated_request_without_authentication() {
		String login = "test";
		String role = "ADMIN";

		HttpServletRequest rq = new HttpServletRequestBuilder().withMethod("GET").build();
		HttpServletResponse rsp = new HttpServletResponseBuilder().build();
		HandlerMethod handlerMethod = getHandlerMethod("nonAuthenticatedMethod");

		Principal principal = createPrincipal(login, role);
		when(securityService.getPrincipal(rq)).thenReturn(Optional.of(principal));

		boolean result = securityInterceptor.preHandle(rq, rsp, handlerMethod);

		assertThat(rsp.getStatus()).isNotEqualTo(403);
		assertThat(result).isTrue();
	}

	private Principal createPrincipal(String login, String role) {
		return new PrincipalBuilder()
				.withLogin(login)
				.withRole(role)
				.build();
	}

	private HandlerMethod getHandlerMethod(String methodName) {
		try {
			TestClass target = new TestClass();
			Method method = TestClass.class.getMethod(methodName);
			return new HandlerMethod(target, method);
		} catch (Exception ex) {
			throw new AssertionError(ex);
		}
	}

	private static class TestClass {
		@Security
		public void authenticatedMethod() {
		}

		@Security(role = "ADMIN")
		public void authenticatedMethodWithRole() {
		}

		public void nonAuthenticatedMethod() {
		}
	}
}
