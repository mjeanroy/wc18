/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.security.mvc;

import com.github.mjeanroy.wc18.security.models.Principal;
import com.github.mjeanroy.wc18.security.service.SecurityService;
import com.github.mjeanroy.wc18.security.tests.builders.HttpServletRequestBuilder;
import com.github.mjeanroy.wc18.security.tests.builders.PrincipalBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.bind.support.DefaultDataBinderFactory;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PrincipalArgumentResolverTest {

	private SecurityService securityService;
	private PrincipalArgumentResolver resolver;

	@Before
	public void setUp() {
		securityService = mock(SecurityService.class);
		resolver = new PrincipalArgumentResolver(securityService);
	}

	@Test
	public void it_should_supports_parameter_with_class_inheriting_from_principal() {
		MethodParameter parameter = new MethodParameter(getMethod1(), 0);
		assertThat(resolver.supportsParameter(parameter)).isTrue();
	}

	@Test
	public void it_should_not_supports_parameter_with_class_not_assignable() {
		MethodParameter parameter = new MethodParameter(getMethod2(), 0);
		assertThat(resolver.supportsParameter(parameter)).isFalse();
	}

	@Test
	public void it_should_get_principal() throws Exception {
		HttpServletRequest rq = new HttpServletRequestBuilder().build();

		MethodParameter methodParameter = new MethodParameter(getMethod1(), 0);
		ModelAndViewContainer mvc = new ModelAndViewContainer();
		NativeWebRequest webRequest = new ServletWebRequest(rq);
		WebDataBinderFactory webDataBinderFactory = new DefaultDataBinderFactory(new ConfigurableWebBindingInitializer());

		Principal principal = createPrincipal(rq);

		Object result = resolver.resolveArgument(methodParameter, mvc, webRequest, webDataBinderFactory);

		assertThat(result).isNotNull().isSameAs(principal);
		verify(securityService).getPrincipal(rq);
	}

	@Test
	public void it_should_return_null_without_principal_authenticated() throws Exception {
		HttpServletRequest rq = new HttpServletRequestBuilder().build();

		MethodParameter methodParameter = new MethodParameter(getMethod1(), 0);
		ModelAndViewContainer mvc = new ModelAndViewContainer();
		NativeWebRequest webRequest = new ServletWebRequest(rq);
		WebDataBinderFactory webDataBinderFactory = new DefaultDataBinderFactory(new ConfigurableWebBindingInitializer());

		Object result = resolver.resolveArgument(methodParameter, mvc, webRequest, webDataBinderFactory);

		assertThat(result).isNull();
		verify(securityService).getPrincipal(rq);
	}

	private Principal createPrincipal(HttpServletRequest rq) {
		Principal principal = new PrincipalBuilder().build();
		when(securityService.getPrincipal(rq)).thenReturn(Optional.of(principal));
		return principal;
	}

	private Method getMethod1() {
		return getMethod("handle1", Principal.class);
	}

	private Method getMethod2() {
		return getMethod("handle2", HttpServletRequest.class);
	}

	private Method getMethod(String name, Class<?> argClass) {
		try {
			return TestControllerClass.class.getMethod(name, argClass);
		} catch (Exception ex) {
			throw new AssertionError(ex);
		}
	}

	private static class TestControllerClass {
		public void handle1(Principal principal) {
		}

		public void handle2(HttpServletRequest rq) {
		}

	}
}
