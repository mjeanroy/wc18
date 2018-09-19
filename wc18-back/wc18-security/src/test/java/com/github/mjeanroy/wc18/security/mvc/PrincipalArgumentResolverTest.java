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

import com.github.mjeanroy.wc18.security.models.Principal;
import com.github.mjeanroy.wc18.security.service.SecurityService;
import com.github.mjeanroy.wc18.security.tests.builders.HttpServletRequestBuilder;
import com.github.mjeanroy.wc18.security.tests.builders.PrincipalBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

class PrincipalArgumentResolverTest {

	private SecurityService securityService;
	private PrincipalArgumentResolver resolver;

	@BeforeEach
	void setUp() {
		securityService = mock(SecurityService.class);
		resolver = new PrincipalArgumentResolver(securityService);
	}

	@Test
	void it_should_supports_parameter_with_class_inheriting_from_principal() {
		MethodParameter parameter = new MethodParameter(getMethod1(), 0);
		assertThat(resolver.supportsParameter(parameter)).isTrue();
	}

	@Test
	void it_should_not_supports_parameter_with_class_not_assignable() {
		MethodParameter parameter = new MethodParameter(getMethod2(), 0);
		assertThat(resolver.supportsParameter(parameter)).isFalse();
	}

	@Test
	void it_should_get_principal() throws Exception {
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
	void it_should_return_null_without_principal_authenticated() throws Exception {
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
