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

import com.github.mjeanroy.wc18.commons.MoreStrings;
import com.github.mjeanroy.wc18.security.Security;
import com.github.mjeanroy.wc18.security.models.Principal;
import com.github.mjeanroy.wc18.security.service.SecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class SecurityInterceptor implements HandlerInterceptor {

	/**
	 * The list of HTTP Request Method that should not be ignored by the interceptor.
	 */
	private static final Set<String> METHOD_REQUIRES_AUTH = new HashSet<String>() {{
		add("GET");
		add("PUT");
		add("POST");
		add("PATCH");
		add("DELETE");
	}};

	private final SecurityService securityService;

	public SecurityInterceptor(SecurityService securityService) {
		this.securityService = securityService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		if (!METHOD_REQUIRES_AUTH.contains(request.getMethod().toUpperCase())) {
			return true;
		}

		Security annotation = getAnnotation(handler);

		if (annotation != null) {
			Optional<Principal> optPrincipal = securityService.getPrincipal(request);

			if (!optPrincipal.isPresent()) {
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				return false;
			}

			Principal principal = optPrincipal.get();
			String requiredRole = annotation.role();
			if (MoreStrings.isNotEmpty(requiredRole) && !Objects.equals(requiredRole, principal.getRole())) {
				response.setStatus(HttpStatus.FORBIDDEN.value());
				return false;
			}

			return true;
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
		// Nothing to do.
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		// Nothing to do.
	}

	private Security getAnnotation(Object handler) {
		if (!(handler instanceof HandlerMethod)) {
			return null;
		}

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		if (handlerMethod.hasMethodAnnotation(Security.class)) {
			return handlerMethod.getMethodAnnotation(Security.class);
		}

		Class<?> beanClass = handlerMethod.getBean().getClass();
		if (beanClass.isAnnotationPresent(Security.class)) {
			return beanClass.getAnnotation(Security.class);
		}

		return null;
	}
}
