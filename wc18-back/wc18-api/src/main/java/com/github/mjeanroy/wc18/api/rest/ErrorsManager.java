/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.rest;

import com.github.mjeanroy.wc18.api.exceptions.CredentialsNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorsManager {

	@ExceptionHandler
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public AppError handleCredentialsNotFound(CredentialsNotFoundException ex) {
		return new AppError(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
	}

	private static class AppError {
		/**
		 * The error status code.
		 */
		private final int status;

		/**
		 * The default error message.
		 */
		private final String message;

		/**
		 * Build the error object.
		 *
		 * @param status Error status.
		 * @param message Error message.
		 */
		private AppError(int status, String message) {
			this.status = status;
			this.message = message;
		}

		/**
		 * Get {@link #status}
		 *
		 * @return {@link #status}
		 */
		public int getStatus() {
			return status;
		}

		/**
		 * Get {@link #message}
		 *
		 * @return {@link #message}
		 */
		public String getMessage() {
			return message;
		}
	}
}
