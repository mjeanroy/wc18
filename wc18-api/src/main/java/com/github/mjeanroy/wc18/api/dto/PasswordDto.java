/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.dto;

import javax.validation.constraints.NotBlank;

public class PasswordDto {

	/**
	 * The login.
	 */
	@NotBlank
	private String oldPassword;

	/**
	 * The password.
	 */
	@NotBlank
	private String newPassword;

	/**
	 * Get {@link #oldPassword}
	 *
	 * @return {@link #oldPassword}
	 */
	public String getOldPassword() {
		return oldPassword;
	}

	/**
	 * Set {@link #oldPassword}
	 *
	 * @param oldPassword New {@link #oldPassword}
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/**
	 * Get {@link #newPassword}
	 *
	 * @return {@link #newPassword}
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * Set {@link #newPassword}
	 *
	 * @param newPassword New {@link #newPassword}
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
