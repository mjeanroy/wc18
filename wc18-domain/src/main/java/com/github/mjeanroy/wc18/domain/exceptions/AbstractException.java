/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.exceptions;

public abstract class AbstractException extends RuntimeException {

	protected AbstractException(String message) {
		super(message);
	}
}
