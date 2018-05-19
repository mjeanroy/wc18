/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.exceptions;

import com.github.mjeanroy.wc18.domain.exceptions.AbstractException;

public class CredentialsNotFoundException extends AbstractException {

	/**
	 * Create the exception.
	 */
	public CredentialsNotFoundException() {
		super("Bad credentials");
	}
}
