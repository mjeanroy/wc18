/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Mickael Jeanroy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.github.mjeanroy.wc18.security.mvc;

import com.github.mjeanroy.wc18.security.Security;
import com.github.mjeanroy.wc18.security.models.Principal;
import com.github.mjeanroy.wc18.security.service.SecurityService;
import com.github.mjeanroy.wc18.security.tests.builders.HttpServletRequestBuilder;
import com.github.mjeanroy.wc18.security.tests.builders.HttpServletResponseBuilder;
import com.github.mjeanroy.wc18.security.tests.builders.PrincipalBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SecurityInterceptorTest {

	private SecurityService securityService;
	private SecurityInterceptor securityInterceptor;

	@BeforeEach
	void setUp() {
		securityService = mock(SecurityService.class);
		securityInterceptor = new SecurityInterceptor(securityService);

		initSecurityService();
	}

	private void initSecurityService() {
		when(securityService.getPrincipal(any(HttpServletRequest.class))).thenReturn(Optional.empty());
	}

	@Test
	void it_should_stop_authenticated_GET_request_without_authentication() {
		doTestWithHttpMethod("GET");
	}

	@Test
	void it_should_stop_authenticated_GET_request_with_required_role() {
		doTestWithHttpMethodAndRequiredRole("GET");
	}

	@Test
	void it_should_stop_authenticated_POST_request_without_authentication() {
		doTestWithHttpMethod("POST");
	}

	@Test
	void it_should_stop_authenticated_POST_request_with_required_role() {
		doTestWithHttpMethodAndRequiredRole("POST");
	}

	@Test
	void it_should_stop_authenticated_PUT_request_without_authentication() {
		doTestWithHttpMethod("PUT");
	}

	@Test
	void it_should_stop_authenticated_PUT_request_with_required_role() {
		doTestWithHttpMethodAndRequiredRole("PUT");
	}

	@Test
	void it_should_stop_authenticated_PATCH_request_without_authentication() {
		doTestWithHttpMethod("PATCH");
	}

	@Test
	void it_should_stop_authenticated_PATCH_request_with_required_role() {
		doTestWithHttpMethodAndRequiredRole("PATCH");
	}

	@Test
	void it_should_stop_authenticated_DELETE_request_without_authentication() {
		doTestWithHttpMethod("DELETE");
	}

	@Test
	void it_should_stop_authenticated_DELETE_request_with_required_role() {
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
	void it_should_not_stop_authenticated_request_with_authentication() {
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
	void it_should_not_stop_authenticated_request_with_authentication_and_required_role() {
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
	void it_should_not_stop_non_authenticated_request_without_authentication() {
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
