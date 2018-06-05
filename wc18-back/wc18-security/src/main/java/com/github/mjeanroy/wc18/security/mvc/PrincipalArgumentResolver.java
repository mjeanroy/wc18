/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.security.mvc;

import com.github.mjeanroy.wc18.security.models.Principal;
import com.github.mjeanroy.wc18.security.service.SecurityService;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Resolver that allow any {@link org.springframework.stereotype.Controller} or {@link org.springframework.web.bind.annotation.RestController}
 * to inject {@link Principal} user in public methods.
 */
public class PrincipalArgumentResolver implements HandlerMethodArgumentResolver {

	private final SecurityService securityService;

	@Inject
	public PrincipalArgumentResolver(SecurityService securityService) {
		this.securityService = securityService;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameter().getType().isAssignableFrom(Principal.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		Object nativeRequest = webRequest.getNativeRequest();
		if (!(nativeRequest instanceof HttpServletRequest)) {
			return null;
		}

		HttpServletRequest rq = (HttpServletRequest) nativeRequest;
		return securityService.getPrincipal(rq).orElse(null);
	}
}
