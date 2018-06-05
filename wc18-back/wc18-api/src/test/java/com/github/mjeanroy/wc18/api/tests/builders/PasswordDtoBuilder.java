/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.tests.builders;

import com.github.mjeanroy.wc18.api.dto.PasswordDto;

/**
 * Builder for {@link PasswordDto} instances.
 */
public class PasswordDtoBuilder {

	/**
	 * The form oldPassword.
	 * @see PasswordDto#oldPassword
	 */
	private String oldPassword;

	/**
	 * The form newPassword.
	 * @see PasswordDto#newPassword
	 */
	private String newPassword;

	/**
	 * Set {@link #oldPassword}
	 *
	 * @param oldPassword New {@link #oldPassword}
	 * @return The builder.
	 */
	public PasswordDtoBuilder withOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
		return this;
	}

	/**
	 * Set {@link #newPassword}
	 *
	 * @param newPassword New {@link #newPassword}
	 * @return The builder.
	 */
	public PasswordDtoBuilder withNewPassword(String newPassword) {
		this.newPassword = newPassword;
		return this;
	}

	/**
	 * Build final {@link PasswordDto} instance.
	 *
	 * @return The final instance.
	 */
	public PasswordDto build() {
		PasswordDto dto = new PasswordDto();
		dto.setOldPassword(oldPassword);
		dto.setNewPassword(newPassword);
		return dto;
	}
}
